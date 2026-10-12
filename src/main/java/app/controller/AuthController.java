package app.controller;

import app.model.User;
import app.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody Map<String, String> request) {
        try {
            User user = authService.register(
                request.get("name"),
                request.get("email"),
                request.get("password"),
                request.getOrDefault("role", "DONOR")
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                    "message", "Registration successful!",
                    "userId", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "role", user.getRole()
                )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                Map.of("message", e.getMessage())
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> request) {
        try {
            User user = authService.login(
                request.get("email"),
                request.get("password")
            );

            return ResponseEntity.ok(
                Map.of(
                    "message", "Sign in successful!",
                    "userId", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "role", user.getRole()
                )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("message", e.getMessage())
            );
        }
    }
}