package com.Trimble_Cars.Repository;

import com.Trimble_Cars.Entity.Car;
import com.Trimble_Cars.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Get all cars owned by a user
    List<Car> findByOwner(User owner);

    // Filter by status: IDLE / ON_LEASE / ON_SERVICE
    List<Car> findByStatus(String status);
}
