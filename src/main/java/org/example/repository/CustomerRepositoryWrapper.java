package org.example.repository;

import java.util.Optional;

import org.example.domain.Customer;
import org.example.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerRepositoryWrapper {

    private CustomerRepository customerRepository;

    @Autowired
    CustomerRepositoryWrapper(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer save(Customer customer){
        return this.customerRepository.save(customer);
    }


    public Customer getById(Long customerId){
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if(customer.isPresent()){
            return customer.get();
        }
        throw new CustomerNotFoundException(customerId);
    }

    public Customer findByPhoneNumber(String phoneNumber){
        Optional<Customer> customer = this.customerRepository.findByPhoneNumber(phoneNumber);
        return customer.isPresent()?customer.get():null;
    }


}
