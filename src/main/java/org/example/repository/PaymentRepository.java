package org.example.repository;

import org.example.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository  extends JpaRepository<Payment, Long>{
    
}
