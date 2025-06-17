package com.Trimble_Cars.Service;

import com.Trimble_Cars.Entity.Car;
import com.Trimble_Cars.Entity.Lease;
import com.Trimble_Cars.Entity.User;
import com.Trimble_Cars.Repository.CarRepository;
import com.Trimble_Cars.Repository.LeaseRepository;
import com.Trimble_Cars.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeaseServiceTest {

    @Mock private LeaseRepository leaseRepository;
    @Mock private CarRepository carRepository;
    @Mock private UserRepository userRepository;
    @InjectMocks private LeaseService leaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void startLease_ShouldReturnSuccess() {
        User user = new User(1L, "Alex","alexrider@hotmail.com", "CUSTOMER", null, null);
        Car car = new Car(2L, "Ford","TMF", "IDLE", null,null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(leaseRepository.countLeaseByCustomer(user)).thenReturn(1L);
        when(carRepository.findById(2L)).thenReturn(Optional.of(car));

        String result = leaseService.startLease(2L, 1L);
        assertEquals("Lease started successfully.", result);
    }

    @Test
    void endLease_ShouldReturnSuccess() {
        Car car = new Car(2L, "Ford","MH2416382", "ON_LEASE", null, null);
        Lease lease = new Lease(); lease.setCar(car);

        when(carRepository.findById(2L)).thenReturn(Optional.of(car));
        when(leaseRepository.findByCarAndEndDateIsNull(car)).thenReturn(Optional.of(lease));

        String result = leaseService.endLease(2L);
        assertEquals("Lease ended successfully.", result);
    }
}
