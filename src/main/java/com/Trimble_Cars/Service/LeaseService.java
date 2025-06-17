package com.Trimble_Cars.Service;

import com.Trimble_Cars.Entity.Car;
import com.Trimble_Cars.Entity.Lease;
import com.Trimble_Cars.Entity.User;
import com.Trimble_Cars.Exception.ResourceNotFoundException;
import com.Trimble_Cars.Repository.CarRepository;
import com.Trimble_Cars.Repository.LeaseRepository;
import com.Trimble_Cars.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeaseService {

    private final LeaseRepository leaseRepo;
    private final CarRepository carRepo;
    private final UserRepository userRepo;

    public String startLease(Long carId, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        if (leaseRepo.countLeaseByCustomer(user) >= 2) {
            return "You can lease max 2 cars at a time.";
        }

        Car car = carRepo.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));

        if (!"IDLE".equalsIgnoreCase(car.getStatus())) {
            return "Car not available.";
        }

        car.setStatus("ON_LEASE");
        carRepo.save(car);

        Lease lease = new Lease();
        lease.setCar(car);
        lease.setCustomer(user);
        lease.setStartDate(LocalDateTime.now());
        leaseRepo.save(lease);

        log.info("Started lease for car {} by user {}", carId, userId);
        return "Lease started successfully.";
    }

    public String endLease(Long carId) {
        Car car = carRepo.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id " + carId));
        Lease lease = leaseRepo.findByCarAndEndDateIsNull(car)
                .orElseThrow(() -> new ResourceNotFoundException("Active lease not found for carId: " + carId));

        lease.setEndDate(LocalDateTime.now());
        leaseRepo.save(lease);

        car.setStatus("IDLE");
        carRepo.save(car);

        log.info("Ended lease for car {}", carId);
        return "Lease ended successfully.";
    }

    public List<Lease> getLeaseHistory(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        return leaseRepo.findByCustomer(user);
    }

    public List<Lease> getAllLeases() {
        return leaseRepo.findAll();
    }
}
