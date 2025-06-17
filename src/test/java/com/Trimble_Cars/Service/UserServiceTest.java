package com.Trimble_Cars.Service;

import com.Trimble_Cars.Entity.User;
import com.Trimble_Cars.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ShouldSaveUser() {
        User user = new User(1L, "John","nicholasjohn@gmail.com", "CUSTOMER", null, null);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.registerUser(user));
    }

    @Test
    void getUserById_ShouldReturnUser() {
        User user = new User(3L, "Adam","johnadams@outlook.com", "ADMIN", null, null);
        when(userRepository.findById(3L)).thenReturn(Optional.of(user));
        assertEquals("Adam", userService.getUserById(3L).getName());
    }

    @Test
    void getAllUsers_ShouldReturnList() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));
        assertEquals(2, userService.getAllUsers().size());
    }
}
