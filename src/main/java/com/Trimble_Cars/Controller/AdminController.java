package com.Trimble_Cars.Controller;

import com.Trimble_Cars.Entity.Car;
import com.Trimble_Cars.Entity.Lease;
import com.Trimble_Cars.Entity.User;
import com.Trimble_Cars.Service.CarOwnerService;
import com.Trimble_Cars.Service.LeaseService;
import com.Trimble_Cars.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CarOwnerService carOwnerService;
    private final LeaseService leaseService;
    private final UserService userService;

    // Admin can register any user
    @PostMapping("/user/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/car/register")
    public ResponseEntity<Car> registerCar(@RequestBody Car car, @RequestParam Long ownerId) {
        return ResponseEntity.ok(carOwnerService.registerCar(car, ownerId));
    }

    @PostMapping("/car/status")
    public ResponseEntity<String> updateCarStatus(@RequestParam Long carId, @RequestParam String status) {
        carOwnerService.updateCarStatus(carId, status);
        return ResponseEntity.ok("Car status updated");
    }

    @PostMapping("/lease/start")
    public ResponseEntity<String> startLease(@RequestParam Long carId, @RequestParam Long userId) {
        String message = leaseService.startLease(carId, userId);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/lease/end")
    public ResponseEntity<String> endLease(@RequestParam Long carId) {
        String message = leaseService.endLease(carId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/leases")
    public ResponseEntity<List<Lease>> getAllLeases() {
        return ResponseEntity.ok(leaseService.getAllLeases());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carOwnerService.getAllCars());
    }
}
