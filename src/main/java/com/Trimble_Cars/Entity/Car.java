package com.Trimble_Cars.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Registration number is mandatory")
    @Column(unique = true)
    private String registrationNumber;

    // Status can be: IDLE, ON_LEASE, ON_SERVICE
    private String status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private Lease activeLease;
}
