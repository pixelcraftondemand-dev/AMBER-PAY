package sl.amberpay.coreapi.rails;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
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
public class RailController {

    private final ConcurrentHashMap<String, RailStatus> railStatuses = new ConcurrentHashMap<>();

    @PostMapping("/rails/{rail}/callbacks")
    public ResponseEntity<Map<String, Object>> callback(
        @PathVariable String rail,
        @Valid @RequestBody RailCallbackRequest request
    ) {
        String normalizedRail = rail == null ? "unknown" : rail.trim();
        railStatuses.put(normalizedRail, new RailStatus(normalizedRail, "healthy", Instant.now().toString(), request.status()));

        return ResponseEntity.ok(Map.of(
            "status", "accepted",
            "rail", normalizedRail,
            "reference", request.reference(),
            "amount_minor", request.amount_minor(),
            "currency", request.currency(),
            "callback_status", request.status()
        ));
    }

    @GetMapping("/rails/{rail}/status")
    public ResponseEntity<Map<String, Object>> status(@PathVariable String rail) {
        String normalizedRail = rail == null ? "unknown" : rail.trim();
        RailStatus status = railStatuses.getOrDefault(normalizedRail,
            new RailStatus(normalizedRail, "healthy", Instant.now().toString(), "unknown"));

        return ResponseEntity.ok(Map.of(
            "rail", status.rail(),
            "status", status.state(),
            "updated_at", status.updated_at(),
            "last_callback_status", status.last_callback_status()
        ));
    }

    public record RailCallbackRequest(
        @NotBlank(message = "status is required") String status,
        @NotBlank(message = "reference is required") String reference,
        long amount_minor,
        @NotBlank(message = "currency is required") String currency
    ) {}

    private record RailStatus(String rail, String state, String updated_at, String last_callback_status) {}
}
