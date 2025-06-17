package com.Trimble_Cars.Controller;

import com.Trimble_Cars.Entity.User;
import com.Trimble_Cars.Exception.UnauthorizedAccessException;
import com.Trimble_Cars.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepo;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        log.info("Registering user: {}", user.getName());
        if (!user.getRole().equalsIgnoreCase("CUSTOMER") && !user.getRole().equalsIgnoreCase("OWNER")) {
            throw new UnauthorizedAccessException("Only CUSTOMER or OWNER roles are allowed.");
        }
        return ResponseEntity.ok(userRepo.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        log.info("Fetching user by ID: {}", id);
        return ResponseEntity.ok(
                userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"))
        );
    }
}
