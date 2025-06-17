package com.Trimble_Cars.Controller;

import com.Trimble_Cars.Entity.Lease;
import com.Trimble_Cars.Repository.UserRepository;
import com.Trimble_Cars.Service.LeaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/lease")
@RequiredArgsConstructor
public class LeaseController {

    private final LeaseService leaseService;
    private final UserRepository userRepo;

    @PostMapping("/start")
    public ResponseEntity<String> startLease(@RequestParam Long carId, @RequestParam Long userId) {
        String message = leaseService.startLease(carId, userId);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/end")
    public ResponseEntity<String> endLease(@RequestParam Long carId) {
        String message = leaseService.endLease(carId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/history")
    public ResponseEntity<List<Lease>> getLeaseHistory(@RequestParam Long userId) {
        return ResponseEntity.ok(leaseService.getLeaseHistory(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lease>> getAllLeases() {
        return ResponseEntity.ok(leaseService.getAllLeases());
    }
}