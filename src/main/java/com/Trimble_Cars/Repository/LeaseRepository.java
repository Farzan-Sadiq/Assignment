package com.Trimble_Cars.Repository;

import com.Trimble_Cars.Entity.Car;
import com.Trimble_Cars.Entity.Lease;
import com.Trimble_Cars.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {

    // Get all leases for a user
    List<Lease> findByCustomer(User customer);

    // Count active leases (to limit 2 leases)
    default long countLeaseByCustomer(User customer) {
        return findByCustomer(customer).stream()
                .filter(lease -> lease.getEndDate() == null)
                .count();
    }

    // Find active lease for a car (not yet returned)
    Optional<Lease> findByCarAndEndDateIsNull(Car car);

    // Get all active leases
    List<Lease> findByEndDateIsNull();
}
