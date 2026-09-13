package sl.amberpay.coreapi.transfer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sl.amberpay.coreapi.auth.AuthUserStore;
import sl.amberpay.coreapi.ledger.LedgerWalletService;

@RestController
@RequestMapping("/v1")
public class TransferController {

    private final AuthUserStore authUserStore;
    private final LedgerWalletService ledgerWalletService;

    public TransferController(AuthUserStore authUserStore, LedgerWalletService ledgerWalletService) {
        this.authUserStore = authUserStore;
        this.ledgerWalletService = ledgerWalletService;
    }

    @PostMapping("/transfers")
    public ResponseEntity<Map<String, Object>> transfer(
        @RequestHeader(value = "Pin-Token", required = false) String pinToken,
        @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
        @Valid @RequestBody TransferRequest request
    ) {
        if (pinToken == null || pinToken.isBlank()) {
            throw new IllegalArgumentException("PIN verification is required to send funds.");
        }
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("Idempotency-Key header is required.");
        }

        AuthUserStore.UserRecord recipient = authUserStore.findByEmailOrPhone(request.recipient_email_or_phone())
            .orElseThrow(() -> new IllegalArgumentException("Recipient not found."));

        String payerUserId = authUserStore.findByEmail("user@example.com")
            .map(AuthUserStore.UserRecord::id)
            .orElseGet(() -> {
                AuthUserStore.UserRecord payer = authUserStore.register(
                    "user@example.com",
                    "+23230000001",
                    "Demo User",
                    "secret"
                );
                return payer.id();
            });

        LedgerWalletService.TransferResult result = ledgerWalletService.postTransfer(
            payerUserId,
            recipient.id(),
            request.amount_minor(),
            request.currency(),
            request.note()
        );

        return ResponseEntity.ok(Map.of(
            "id", result.id(),
            "status", result.status(),
            "journal_id", result.journalId(),
            "fee_minor", result.feeMinor(),
            "tax_minor", result.taxMinor(),
            "total_minor", result.totalMinor()
        ));
    }

    @GetMapping("/transfers/{id}")
    public ResponseEntity<Map<String, Object>> transferById(@PathVariable String id) {
        LedgerWalletService.TransferResult result = ledgerWalletService.getTransfer(id)
            .orElseThrow(() -> new IllegalArgumentException("Transfer not found."));

        return ResponseEntity.ok(Map.of(
            "id", result.id(),
            "status", result.status(),
            "journal_id", result.journalId(),
            "fee_minor", result.feeMinor(),
            "tax_minor", result.taxMinor(),
            "total_minor", result.totalMinor()
        ));
    }

    public record TransferRequest(
        @NotBlank(message = "recipient_email_or_phone is required") String recipient_email_or_phone,
        @Min(value = 1, message = "amount_minor must be positive") long amount_minor,
        @NotBlank(message = "currency is required") String currency,
        String note
    ) {}
}
