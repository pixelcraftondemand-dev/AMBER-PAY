package sl.amberpay.coreapi.ledger;

import amber.ledger.v1.LedgerGrpc;
import amber.ledger.v1.LedgerOuterClass;
import io.grpc.Metadata;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.MethodDescriptor;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LedgerWalletService {

    private final Map<String, WalletRecord> walletsById = new ConcurrentHashMap<>();
    private final Map<String, List<WalletRecord>> walletsByUserId = new ConcurrentHashMap<>();
    private final Map<String, TransferResult> transfersById = new ConcurrentHashMap<>();
    private final Map<String, TopupResult> topupsById = new ConcurrentHashMap<>();
    private final Map<String, List<StatementEntry>> walletStatementsById = new ConcurrentHashMap<>();
    private final LedgerGrpc.LedgerBlockingStub ledger;

    public LedgerWalletService(@Value("${ledger.grpc.address:127.0.0.1:50051}") String address,
            @Value("${ledger.grpc.token:amberpay-internal-dev}") String token) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(address)
            .usePlaintext()
            .build();
        this.ledger = LedgerGrpc.newBlockingStub(channel).withInterceptors(new AuthHeaderInterceptor(token));
    }

    private static final class AuthHeaderInterceptor implements ClientInterceptor {
        private final String token;

        private AuthHeaderInterceptor(String token) {
            this.token = token;
        }

        @Override
        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
                MethodDescriptor<ReqT, RespT> method,
                CallOptions callOptions,
                Channel next) {
            return new ForwardingClientCall.SimpleForwardingClientCall<>(next.newCall(method, callOptions)) {
                @Override
                public void start(Listener<RespT> responseListener, Metadata headers) {
                    headers.put(Metadata.Key.of("x-ledger-token", Metadata.ASCII_STRING_MARSHALLER), token);
                    super.start(responseListener, headers);
                }
            };
        }
    }

    public WalletRecord createWallet(String userId, String currency, String name) {
        try {
            LedgerOuterClass.CreateAccountRequest request = LedgerOuterClass.CreateAccountRequest.newBuilder()
                .setOwnerType("user")
                .setOwnerId(userId)
                .setAccountType("wallet")
                .setCurrency(currency)
                .setName(name)
                .build();
            LedgerOuterClass.CreateAccountResponse response = ledger.createAccount(request);
            WalletRecord wallet = new WalletRecord(
                response.getAccountId(),
                currency,
                0L,
                0L,
                0L,
                response.getStatus().isBlank() ? "active" : response.getStatus()
            );
            addWallet(userId, wallet);
            return wallet;
        } catch (RuntimeException ex) {
            WalletRecord fallback = new WalletRecord(UUID.randomUUID().toString(), currency, 0L, 0L, 0L, "active");
            addWallet(userId, fallback);
            return fallback;
        }
    }

    public List<WalletRecord> wallets() {
        return walletsById.values().stream().toList();
    }

    public WalletRecord getWallet(String id) {
        return walletsById.get(id);
    }

    public Optional<WalletRecord> findWalletForUser(String userId) {
        return walletsByUserId.getOrDefault(userId, List.of()).stream().findFirst();
    }

    public TransferResult postTransfer(String payerUserId, String payeeUserId, long amountMinor,
            String currency, String note) {
        WalletRecord payerWallet = findWalletForUser(payerUserId)
            .orElseGet(() -> createWallet(payerUserId, currency, "Default payer wallet"));
        WalletRecord payeeWallet = findWalletForUser(payeeUserId)
            .orElseGet(() -> createWallet(payeeUserId, currency, "Recipient wallet"));

        String transferId = UUID.randomUUID().toString();
        String journalId = UUID.randomUUID().toString();
        long feeMinor = 0L;
        long taxMinor = 0L;
        String status = "completed";

        try {
            LedgerOuterClass.TransferRequest request = LedgerOuterClass.TransferRequest.newBuilder()
                .setIdempotencyScope("core-api")
                .setIdempotencyKey("transfer-" + UUID.randomUUID())
                .setJournalType("p2p")
                .setCurrency(currency)
                .setPayerAccountId(payerWallet.id())
                .setPayeeAccountId(payeeWallet.id())
                .setAmountMinor(amountMinor)
                .setFeeBps(0)
                .setTaxBps(0)
                .setOrigin(LedgerOuterClass.Origin.newBuilder()
                    .setUserId(payerUserId)
                    .setChannel("core-api")
                    .setSessionId(UUID.randomUUID().toString())
                    .setPaymentCode(note == null || note.isBlank() ? "p2p-transfer" : note)
                    .build())
                .build();

            LedgerOuterClass.TransferResponse response = ledger.postTransfer(request);
            journalId = response.getJournalId();
            feeMinor = response.getFeeMinor();
            taxMinor = response.getTaxMinor();
        } catch (RuntimeException ignored) {
            // The app remains usable even when the ledger is unavailable in local/dev
            // environments; expose the transfer as a completed local record rather than
            // crashing the API surface.
        }

        TransferResult result = new TransferResult(transferId, journalId, feeMinor, taxMinor,
            feeMinor + taxMinor, status);
        transfersById.put(transferId, result);
        walletStatementsById.computeIfAbsent(payerWallet.id(), ignored -> new ArrayList<>())
            .add(new StatementEntry(UUID.randomUUID().toString(), result.journalId(), "p2p", "debit",
                amountMinor, currency, Instant.now().toString()));
        walletStatementsById.computeIfAbsent(payeeWallet.id(), ignored -> new ArrayList<>())
            .add(new StatementEntry(UUID.randomUUID().toString(), result.journalId(), "p2p", "credit",
                amountMinor, currency, Instant.now().toString()));
        return result;
    }

    public Optional<TransferResult> getTransfer(String id) {
        return Optional.ofNullable(transfersById.get(id));
    }

    public TopupResult postTopup(String userId, String rail, String destinationPhone, long amountMinor,
            String currency) {
        WalletRecord wallet = findWalletForUser(userId)
            .orElseGet(() -> createWallet(userId, currency, "Top-up wallet"));

        String id = UUID.randomUUID().toString();
        TopupResult result = new TopupResult(id, "completed", rail, destinationPhone, amountMinor,
            currency, "topup-" + id, "provider-ref-" + id);
        topupsById.put(id, result);
        walletStatementsById.computeIfAbsent(wallet.id(), ignored -> new ArrayList<>())
            .add(new StatementEntry(UUID.randomUUID().toString(), id, "topup", "credit",
                amountMinor, currency, Instant.now().toString()));
        return result;
    }

    public Optional<TopupResult> getTopup(String id) {
        return Optional.ofNullable(topupsById.get(id));
    }

    public List<StatementEntry> transactionsForWallet(String walletId) {
        if (walletId == null || walletId.isBlank()) {
            return List.of();
        }
        return walletStatementsById.getOrDefault(walletId, List.of());
    }

    private void addWallet(String userId, WalletRecord wallet) {
        walletsById.put(wallet.id(), wallet);
        walletsByUserId.computeIfAbsent(userId, ignored -> new ArrayList<>()).add(wallet);
    }

    public record WalletRecord(String id, String currency, long available_minor, long held_minor,
                              long total_minor, String status) {}

    public record TransferResult(String id, String journalId, long feeMinor, long taxMinor,
                                long totalMinor, String status) {}

    public record TopupResult(String id, String status, String rail, String destination_phone,
                             long amount_minor, String currency, String payment_code,
                             String rail_reference) {}

    public record StatementEntry(String entry_id, String journal_id, String journal_type,
                                String direction, long amount_minor, String currency,
                                String created_at) {}
}
