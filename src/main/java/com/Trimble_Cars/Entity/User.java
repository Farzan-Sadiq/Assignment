package com.Trimble_Cars.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "User name is mandatory")
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String role; // CUSTOMER, OWNER, ADMIN

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Car> cars;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Lease> leases;
}
