package com.Trimble_Cars.Service;

import com.Trimble_Cars.Entity.User;
import com.Trimble_Cars.Exception.ResourceNotFoundException;
import com.Trimble_Cars.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public User registerUser(User user) {
        log.info("Registering user: {}", user.getName());
        return userRepo.save(user);
    }

    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
