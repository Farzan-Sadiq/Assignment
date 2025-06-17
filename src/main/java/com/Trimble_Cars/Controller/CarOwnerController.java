package com.Trimble_Cars.Controller;

import com.Trimble_Cars.Entity.Car;
import com.Trimble_Cars.Service.CarOwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@Slf4j
public class CarOwnerController {
    private final CarOwnerService carOwnerService;

    @PostMapping("/register")
    public ResponseEntity<Car> registerCar(@Valid @RequestBody Car car, @RequestParam Long ownerId) {
        log.info("Registering car: {} for ownerId {}", car.getModel(), ownerId);
        return ResponseEntity.ok(carOwnerService.registerCar(car, ownerId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        log.info("Fetching all cars");
        return ResponseEntity.ok(carOwnerService.getAllCars());
    }
}
