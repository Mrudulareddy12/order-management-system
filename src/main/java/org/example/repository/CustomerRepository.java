package org.example.repository;

import java.util.Optional;

import org.example.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findById(Long id);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
