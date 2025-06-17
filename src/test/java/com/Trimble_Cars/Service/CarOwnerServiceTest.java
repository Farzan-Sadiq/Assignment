package com.Trimble_Cars.Service;

import com.Trimble_Cars.Entity.Car;
import com.Trimble_Cars.Entity.User;
import com.Trimble_Cars.Repository.CarRepository;
import com.Trimble_Cars.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarOwnerServiceTest {

    @Mock private CarRepository carRepository;
    @Mock private UserRepository userRepository;
    @InjectMocks private CarOwnerService carOwnerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerCar_ShouldSaveCar() {
        User owner = new User(1L, "Owner","owneruser@yahoo.com", "OWNER", null, null);
        Car car = new Car(null, "Honda","DL1297362", "IDLE", owner,null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(owner));
        when(carRepository.save(any(Car.class))).thenReturn(car);

        Car saved = carOwnerService.registerCar(car, 1L);
        assertEquals("Honda", saved.getModel());
    }

    @Test
    void updateCarStatus_ShouldUpdateStatus() {
        Car car = new Car(1L, "Toyota","HR7293729", "IDLE", null,null);
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        carOwnerService.updateCarStatus(1L, "ON_LEASE");
        verify(carRepository).save(car);
        assertEquals("ON_LEASE", car.getStatus());
    }
}