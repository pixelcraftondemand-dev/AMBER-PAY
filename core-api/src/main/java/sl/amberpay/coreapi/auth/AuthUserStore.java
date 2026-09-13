package sl.amberpay.coreapi.auth;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserStore {

    private final Map<String, UserRecord> usersByEmail = new ConcurrentHashMap<>();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void seedDefaults() {
        register("user@example.com", "+23230000001", "Demo User", "secret");
    }

    public UserRecord register(String email, String phone, String fullName, String rawPassword) {
        String normalizedEmail = normalizeEmail(email);
        if (usersByEmail.containsKey(normalizedEmail)) {
            throw new IllegalArgumentException("User already exists.");
        }
        UserRecord user = new UserRecord(
            UUID.randomUUID().toString(),
            normalizedEmail,
            phone,
            fullName,
            passwordEncoder.encode(rawPassword)
        );
        usersByEmail.put(normalizedEmail, user);
        return user;
    }

    public Optional<UserRecord> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(normalizeEmail(email)));
    }

    public Optional<UserRecord> findByEmailOrPhone(String emailOrPhone) {
        if (emailOrPhone == null || emailOrPhone.isBlank()) {
            return Optional.empty();
        }
        String normalized = emailOrPhone.trim();
        return usersByEmail.values().stream()
            .filter(user -> user.email().equalsIgnoreCase(normalized) || user.phone().equalsIgnoreCase(normalized))
            .findFirst();
    }

    public List<UserRecord> allUsers() {
        return List.copyOf(usersByEmail.values());
    }

    private String normalizeEmail(String email) {
        return email == null ? "" : email.trim().toLowerCase();
    }

    public record UserRecord(String id, String email, String phone, String fullName, String passwordHash) {}
}
