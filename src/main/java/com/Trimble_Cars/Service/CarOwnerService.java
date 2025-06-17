package com.Trimble_Cars.Service;

import com.Trimble_Cars.Entity.Car;
import com.Trimble_Cars.Entity.User;
import com.Trimble_Cars.Exception.ResourceNotFoundException;
import com.Trimble_Cars.Repository.CarRepository;
import com.Trimble_Cars.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarOwnerService {

    private final CarRepository carRepo;
    private final UserRepository userRepo;

    public Car registerCar(Car car, Long ownerId) {
        User owner = userRepo.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + ownerId));
        car.setOwner(owner);
        car.setStatus("IDLE");
        log.info("Registering car {} for owner {}", car.getModel(), owner.getName());
        return carRepo.save(car);
    }

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public void updateCarStatus(Long carId, String status) {
        Car car = carRepo.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
        car.setStatus(status.toUpperCase());
        carRepo.save(car);
        log.info("Updated car {} status to {}", carId, status);
    }
}
