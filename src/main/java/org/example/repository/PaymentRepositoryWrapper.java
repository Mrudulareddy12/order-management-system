package org.example.repository;

import org.example.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentRepositoryWrapper {
    private PaymentRepository paymentRepository;

    @Autowired
    PaymentRepositoryWrapper(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment save(Payment paymentDetails){
        return this.paymentRepository.save(paymentDetails);
    }
}
