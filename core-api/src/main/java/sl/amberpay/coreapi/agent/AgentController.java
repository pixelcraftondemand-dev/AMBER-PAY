package sl.amberpay.coreapi.agent;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sl.amberpay.coreapi.auth.AuthUserStore;
import sl.amberpay.coreapi.ledger.LedgerWalletService;

@RestController
@RequestMapping("/v1")
public class AgentController {

    private final AuthUserStore authUserStore;
    private final LedgerWalletService ledgerWalletService;
    private final AtomicLong agentFloatMinor = new AtomicLong(0L);
    private final AtomicLong todayVolumeMinor = new AtomicLong(0L);

    public AgentController(AuthUserStore authUserStore, LedgerWalletService ledgerWalletService) {
        this.authUserStore = authUserStore;
        this.ledgerWalletService = ledgerWalletService;
    }

    @PostMapping("/agents/cash-in")
    public ResponseEntity<Map<String, Object>> cashIn(@Valid @RequestBody CashMovementRequest request) {
        AuthUserStore.UserRecord user = resolveUser(request.user_phone());
        LedgerWalletService.WalletRecord wallet = ledgerWalletService.findWalletForUser(user.id())
            .orElseGet(() -> ledgerWalletService.createWallet(user.id(), request.currency(), user.fullName() + " wallet"));

        long amount = request.amount_minor();
        agentFloatMinor.addAndGet(amount);
        todayVolumeMinor.addAndGet(amount);

        return ResponseEntity.ok(Map.of(
            "id", UUID.randomUUID().toString(),
            "status", "completed",
            "user_phone", request.user_phone(),
            "customer_wallet_id", wallet.id(),
            "amount_minor", amount,
            "currency", request.currency(),
            "agent_float_minor", agentFloatMinor.get(),
            "today_volume_minor", todayVolumeMinor.get()
        ));
    }

    @PostMapping("/agents/cash-out")
    public ResponseEntity<Map<String, Object>> cashOut(@Valid @RequestBody CashMovementRequest request) {
        AuthUserStore.UserRecord user = resolveUser(request.user_phone());
        LedgerWalletService.WalletRecord wallet = ledgerWalletService.findWalletForUser(user.id())
            .orElseGet(() -> ledgerWalletService.createWallet(user.id(), request.currency(), user.fullName() + " wallet"));

        long amount = request.amount_minor();
        agentFloatMinor.addAndGet(amount);
        todayVolumeMinor.addAndGet(amount);

        return ResponseEntity.ok(Map.of(
            "id", UUID.randomUUID().toString(),
            "status", "completed",
            "user_phone", request.user_phone(),
            "customer_wallet_id", wallet.id(),
            "amount_minor", amount,
            "currency", request.currency(),
            "agent_float_minor", agentFloatMinor.get(),
            "today_volume_minor", todayVolumeMinor.get()
        ));
    }

    @GetMapping("/agents/float")
    public ResponseEntity<Map<String, Object>> floatSummary() {
        return ResponseEntity.ok(Map.of(
            "float_minor", agentFloatMinor.get(),
            "today_volume_minor", todayVolumeMinor.get(),
            "currency", "SLE"
        ));
    }

    private AuthUserStore.UserRecord resolveUser(String userPhone) {
        return authUserStore.findByEmailOrPhone(userPhone)
            .orElseGet(() -> authUserStore.register(
                userPhone.replace("+", "") + "@example.com",
                userPhone,
                "Agent customer",
                "secret"
            ));
    }

    public record CashMovementRequest(
        @NotBlank(message = "user_phone is required") String user_phone,
        @Min(value = 1, message = "amount_minor must be positive") long amount_minor,
        @NotBlank(message = "currency is required") String currency
    ) {}
}
