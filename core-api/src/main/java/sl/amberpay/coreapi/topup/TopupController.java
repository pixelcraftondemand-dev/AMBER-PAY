package sl.amberpay.coreapi.topup;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
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
public class TopupController {

    private final AuthUserStore authUserStore;
    private final LedgerWalletService ledgerWalletService;

    public TopupController(AuthUserStore authUserStore, LedgerWalletService ledgerWalletService) {
        this.authUserStore = authUserStore;
        this.ledgerWalletService = ledgerWalletService;
    }

    @PostMapping("/topups")
    public ResponseEntity<Map<String, Object>> topup(
        @RequestHeader(value = "Pin-Token", required = false) String pinToken,
        @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
        @Valid @RequestBody TopupRequest request
    ) {
        if (pinToken == null || pinToken.isBlank()) {
            throw new IllegalArgumentException("PIN verification is required to top up your wallet.");
        }
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("Idempotency-Key header is required.");
        }

        String userId = authUserStore.findByEmail("user@example.com")
            .map(AuthUserStore.UserRecord::id)
            .orElseGet(() -> authUserStore.register("user@example.com", "+23230000001", "Demo User", "secret").id());

        LedgerWalletService.TopupResult result = ledgerWalletService.postTopup(
            userId,
            request.rail(),
            request.destination_phone(),
            request.amount_minor(),
            request.currency()
        );

        return ResponseEntity.ok(Map.of(
            "id", result.id(),
            "status", result.status(),
            "rail", result.rail(),
            "destination_phone", result.destination_phone(),
            "amount_minor", result.amount_minor(),
            "currency", result.currency(),
            "rail_reference", result.rail_reference()
        ));
    }

    @GetMapping("/topups/{id}")
    public ResponseEntity<Map<String, Object>> topupById(@PathVariable String id) {
        LedgerWalletService.TopupResult result = ledgerWalletService.getTopup(id)
            .orElseThrow(() -> new IllegalArgumentException("Top-up not found."));

        return ResponseEntity.ok(Map.of(
            "id", result.id(),
            "status", result.status(),
            "rail", result.rail(),
            "destination_phone", result.destination_phone(),
            "amount_minor", result.amount_minor(),
            "currency", result.currency(),
            "rail_reference", result.rail_reference()
        ));
    }

    public record TopupRequest(
        @NotBlank(message = "rail is required") String rail,
        @NotBlank(message = "destination_phone is required") String destination_phone,
        @Min(value = 1, message = "amount_minor must be positive") long amount_minor,
        @NotBlank(message = "currency is required") String currency
    ) {}
}
