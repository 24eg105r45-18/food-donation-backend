package app.service;

import app.model.User;
import app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String name, String email,
                         String password, String role) {

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                "This email is already registered.");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException(
                "Invalid email or password."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException(
                "Invalid email or password.");
        }

        return user;
    }
}