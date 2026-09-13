package sl.amberpay.coreapi.api;

import java.util.Map;

public record ApiErrorBody(Error error) {
    public record Error(String code, String message, String request_id, String field) {}

    public static ApiErrorBody from(String code, String message, String requestId, String field) {
        return new ApiErrorBody(new Error(code, message, requestId, field));
    }

    public Map<String, Object> toMap() {
        return Map.of(
            "error",
            Map.of(
                "code", error.code(),
                "message", error.message(),
                "request_id", error.request_id() == null ? "" : error.request_id(),
                "field", error.field() == null ? "" : error.field()
            )
        );
    }
}
