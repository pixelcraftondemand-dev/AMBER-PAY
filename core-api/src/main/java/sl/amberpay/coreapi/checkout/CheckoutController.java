package sl.amberpay.coreapi.checkout;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CheckoutController {

    private final Map<String, CheckoutRecord> checkouts = new ConcurrentHashMap<>();

    @PostMapping("/checkouts")
    public ResponseEntity<Map<String, Object>> createCheckout(
        @RequestHeader(value = "X-API-Key", required = false) String apiKey,
        @Valid @RequestBody CheckoutRequest request
    ) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("X-API-Key header is required.");
        }

        String id = "chk_" + UUID.randomUUID();
        CheckoutRecord checkout = new CheckoutRecord(
            id,
            "created",
            request.amount_minor(),
            request.currency(),
            request.payment_method(),
            request.buyer(),
            request.payment_code(),
            request.return_url(),
            request.metadata() == null ? Map.of() : request.metadata(),
            "https://checkout.amberpay.sl/" + id
        );
        checkouts.put(id, checkout);

        return ResponseEntity.ok(Map.of(
            "id", checkout.id(),
            "status", checkout.status(),
            "checkout_url", checkout.checkout_url()
        ));
    }

    @GetMapping("/checkouts/{id}")
    public ResponseEntity<Map<String, Object>> checkoutById(@PathVariable String id) {
        CheckoutRecord checkout = checkouts.get(id);
        if (checkout == null) {
            throw new IllegalArgumentException("Checkout not found.");
        }

        return ResponseEntity.ok(Map.of(
            "id", checkout.id(),
            "status", checkout.status(),
            "amount_minor", checkout.amount_minor(),
            "currency", checkout.currency(),
            "payment_method", checkout.payment_method(),
            "payment_code", checkout.payment_code(),
            "buyer", Map.of(
                "email", checkout.buyer().email(),
                "phone", checkout.buyer().phone()
            ),
            "return_url", checkout.return_url(),
            "metadata", checkout.metadata(),
            "checkout_url", checkout.checkout_url()
        ));
    }

    @PostMapping("/checkouts/{id}/confirm")
    public ResponseEntity<Map<String, Object>> confirmCheckout(
        @PathVariable String id,
        @Valid @RequestBody ConfirmRequest request
    ) {
        CheckoutRecord checkout = checkouts.get(id);
        if (checkout == null) {
            throw new IllegalArgumentException("Checkout not found.");
        }
        if (request.pin_token() == null || request.pin_token().isBlank()) {
            throw new IllegalArgumentException("pin_token is required.");
        }

        CheckoutRecord updated = new CheckoutRecord(
            checkout.id(),
            "succeeded",
            checkout.amount_minor(),
            checkout.currency(),
            checkout.payment_method(),
            checkout.buyer(),
            checkout.payment_code(),
            checkout.return_url(),
            checkout.metadata(),
            checkout.checkout_url()
        );
        checkouts.put(id, updated);

        return ResponseEntity.ok(Map.of(
            "id", updated.id(),
            "status", updated.status(),
            "amount_minor", updated.amount_minor(),
            "currency", updated.currency(),
            "payment_method", updated.payment_method(),
            "payment_code", updated.payment_code(),
            "checkout_url", updated.checkout_url()
        ));
    }

    @PostMapping("/checkouts/{id}/cancel")
    public ResponseEntity<Map<String, Object>> cancelCheckout(@PathVariable String id) {
        CheckoutRecord checkout = checkouts.get(id);
        if (checkout == null) {
            throw new IllegalArgumentException("Checkout not found.");
        }

        CheckoutRecord updated = new CheckoutRecord(
            checkout.id(),
            "cancelled",
            checkout.amount_minor(),
            checkout.currency(),
            checkout.payment_method(),
            checkout.buyer(),
            checkout.payment_code(),
            checkout.return_url(),
            checkout.metadata(),
            checkout.checkout_url()
        );
        checkouts.put(id, updated);

        return ResponseEntity.ok(Map.of(
            "id", updated.id(),
            "status", updated.status(),
            "amount_minor", updated.amount_minor(),
            "currency", updated.currency(),
            "payment_method", updated.payment_method(),
            "payment_code", updated.payment_code()
        ));
    }

    @PostMapping("/checkouts/{id}/refund")
    public ResponseEntity<Map<String, Object>> refundCheckout(
        @PathVariable String id,
        @Valid @RequestBody RefundRequest request
    ) {
        CheckoutRecord checkout = checkouts.get(id);
        if (checkout == null) {
            throw new IllegalArgumentException("Checkout not found.");
        }

        CheckoutRecord updated = new CheckoutRecord(
            checkout.id(),
            "refunded",
            checkout.amount_minor(),
            checkout.currency(),
            checkout.payment_method(),
            checkout.buyer(),
            checkout.payment_code(),
            checkout.return_url(),
            checkout.metadata(),
            checkout.checkout_url()
        );
        checkouts.put(id, updated);

        return ResponseEntity.ok(Map.of(
            "id", updated.id(),
            "status", updated.status(),
            "amount_minor", request.amount_minor(),
            "currency", updated.currency(),
            "payment_method", updated.payment_method(),
            "payment_code", updated.payment_code()
        ));
    }

    public record CheckoutRequest(
        @Min(value = 1, message = "amount_minor must be positive") long amount_minor,
        @NotBlank(message = "currency is required") String currency,
        @NotBlank(message = "payment_method is required") String payment_method,
        @NotNull(message = "buyer is required") Buyer buyer,
        @NotBlank(message = "payment_code is required") String payment_code,
        @NotBlank(message = "return_url is required") String return_url,
        Map<String, Object> metadata
    ) {}

    public record Buyer(
        @NotBlank(message = "buyer.email is required") String email,
        @NotBlank(message = "buyer.phone is required") String phone
    ) {}

    public record ConfirmRequest(
        @NotBlank(message = "pin_token is required") String pin_token
    ) {}

    public record RefundRequest(
        @Min(value = 1, message = "amount_minor must be positive") long amount_minor
    ) {}

    private record CheckoutRecord(
        String id,
        String status,
        long amount_minor,
        String currency,
        String payment_method,
        Buyer buyer,
        String payment_code,
        String return_url,
        Map<String, Object> metadata,
        String checkout_url
    ) {}
}
