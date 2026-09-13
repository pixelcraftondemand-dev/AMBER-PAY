package sl.amberpay.coreapi.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sl.amberpay.coreapi.ledger.LedgerWalletService;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthUserStore authUserStore;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final LedgerWalletService ledgerWalletService;

    public AuthController(AuthUserStore authUserStore, LedgerWalletService ledgerWalletService) {
        this.authUserStore = authUserStore;
        this.ledgerWalletService = ledgerWalletService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request) {
        AuthUserStore.UserRecord user = authUserStore.findByEmail(request.email())
            .orElseThrow(() -> new IllegalArgumentException("Email or password is incorrect."));
        if (!passwordEncoder.matches(request.password(), user.passwordHash())) {
            throw new IllegalArgumentException("Email or password is incorrect.");
        }
        return ResponseEntity.ok(Map.of(
            "access_token", "access-token-" + UUID.randomUUID(),
            "refresh_token", "refresh-token-" + UUID.randomUUID()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterRequest request) {
        AuthUserStore.UserRecord user = authUserStore.register(
            request.email(),
            request.phone(),
            request.full_name(),
            request.password()
        );

        LedgerWalletService.WalletRecord wallet = ledgerWalletService.createWallet(
            user.id(),
            "SLE",
            request.full_name() + " wallet"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
            "user", Map.of(
                "id", user.id(),
                "email", user.email(),
                "phone", user.phone(),
                "full_name", user.fullName()
            ),
            "wallet", Map.of(
                "id", wallet.id(),
                "currency", wallet.currency(),
                "available_minor", wallet.available_minor(),
                "held_minor", wallet.held_minor(),
                "total_minor", wallet.total_minor(),
                "status", wallet.status()
            )
        ));
    }

    @PostMapping("/pin/verify")
    public ResponseEntity<Map<String, String>> verifyPin(@Valid @RequestBody PinVerifyRequest request) {
        if (!request.pin().matches("\\d{4,6}")) {
            throw new IllegalArgumentException("PIN must be 4 to 6 digits.");
        }
        return ResponseEntity.ok(Map.of("pin_token", "pin-token-" + UUID.randomUUID()));
    }

    @PostMapping("/otp/request")
    public ResponseEntity<Map<String, String>> requestOtp(@Valid @RequestBody OtpRequest request) {
        return ResponseEntity.ok(Map.of("status", "sent", "purpose", request.purpose()));
    }

    public record LoginRequest(
        @NotBlank(message = "email is required") String email,
        @NotBlank(message = "password is required") String password
    ) {}

    public record RegisterRequest(
        @NotBlank(message = "email is required") String email,
        @NotBlank(message = "phone is required") String phone,
        @NotBlank(message = "full_name is required") String full_name,
        @NotBlank(message = "password is required") String password
    ) {}

    public record PinVerifyRequest(
        @NotBlank(message = "pin is required")
        @Pattern(regexp = "\\d{4,6}", message = "PIN must be 4 to 6 digits")
        String pin
    ) {}

    public record OtpRequest(
        @NotBlank(message = "purpose is required") String purpose
    ) {}
}
