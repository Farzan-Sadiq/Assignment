package com.Trimble_Cars.Repository;

import com.Trimble_Cars.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find by name (can help admin/customer search)
    Optional<User> findByName(String name);

    // Find all users with specific role
    Optional<User> findByRole(String role);
}
