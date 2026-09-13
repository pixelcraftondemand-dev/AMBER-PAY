package sl.amberpay.coreapi.paymentmethod;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class PaymentMethodController {

    private final ConcurrentHashMap<String, PaymentMethodRecord> methods = new ConcurrentHashMap<>();

    @PostMapping("/payment-methods/cards")
    public ResponseEntity<Map<String, Object>> saveCard(@Valid @RequestBody SaveCardRequest request) {
        String id = "pm_" + UUID.randomUUID();
        PaymentMethodRecord method = new PaymentMethodRecord(
            id,
            "card",
            request.psp_token(),
            "active",
            "https://checkout.amberpay.sl/3ds/" + id
        );
        methods.put(id, method);

        return ResponseEntity.ok(Map.of(
            "id", method.id(),
            "type", method.type(),
            "status", method.status(),
            "psp_token", method.psp_token(),
            "redirect_url", method.redirect_url()
        ));
    }

    @GetMapping("/payment-methods")
    public ResponseEntity<Map<String, Object>> listMethods() {
        return ResponseEntity.ok(Map.of(
            "methods", methods.values().stream()
                .map(method -> Map.of(
                    "id", method.id(),
                    "type", method.type(),
                    "status", method.status(),
                    "psp_token", method.psp_token()
                ))
                .toList()
        ));
    }

    @PostMapping("/payment-methods/cards/{id}/3ds-challenge")
    public ResponseEntity<Map<String, Object>> completeThreeDsChallenge(
        @PathVariable String id,
        @Valid @RequestBody ChallengeRequest request
    ) {
        PaymentMethodRecord method = methods.get(id);
        if (method == null) {
            throw new IllegalArgumentException("Payment method not found.");
        }

        PaymentMethodRecord updated = new PaymentMethodRecord(
            method.id(),
            method.type(),
            method.psp_token(),
            "verified",
            method.redirect_url()
        );
        methods.put(id, updated);

        return ResponseEntity.ok(Map.of(
            "id", updated.id(),
            "status", updated.status(),
            "redirect_url", "https://checkout.amberpay.sl/complete/" + id + "?challenge=" + request.challenge_response()
        ));
    }

    public record SaveCardRequest(
        @NotBlank(message = "psp_token is required") String psp_token
    ) {}

    public record ChallengeRequest(
        @NotBlank(message = "challenge_response is required") String challenge_response
    ) {}

    private record PaymentMethodRecord(
        String id,
        String type,
        String psp_token,
        String status,
        String redirect_url
    ) {}
}
