package org.example.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.example.data.CustomerData;
import org.example.data.ResourceId;
import org.example.domain.Customer;
import org.example.exception.CustomerAlreadyExistsException;
import org.example.repository.CustomerRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepositoryWrapper customerRepositoryWrapper;

    @Autowired
    CustomerServiceImpl(final CustomerRepositoryWrapper customerRepositoryWrapper) {
        this.customerRepositoryWrapper = customerRepositoryWrapper;
    }

    @Override
    public CustomerData getCustomer(Long id) {
        CustomerData data = null;
        Customer customerDetails = this.customerRepositoryWrapper.getById(id);
        if (customerDetails != null) {
            data = CustomerData.builder().address(customerDetails.getAddress()).email(customerDetails.getEmail())
                    .name(customerDetails.getName()).phoneNumber(customerDetails.getPhoneNumber())
                    .id(customerDetails.getId()).build();
        }
        return data;
    }

    @Override
    public ResourceId saveCustomer(CustomerData customer) {
        Customer existingCustomerDetails = this.customerRepositoryWrapper.findByPhoneNumber(customer.getPhoneNumber());
        if(existingCustomerDetails!=null){
            throw new CustomerAlreadyExistsException(customer.getPhoneNumber());
        }
        Customer customerDetails = new Customer();
        customerDetails.setAddress(customer.getAddress());
        customerDetails.setEmail(customer.getEmail());
        customerDetails.setName(customer.getName());
        customerDetails.setPhoneNumber(customer.getPhoneNumber());
        Instant instant = Instant.now();
        Timestamp currentTimestamp = Timestamp.from(instant);
        customerDetails.setCreatedAt(currentTimestamp);
        customerDetails.setUpdatedAt(currentTimestamp);
        Customer details = this.customerRepositoryWrapper.save(customerDetails);
        return ResourceId.builder().id(details.getId()).build();

    }

}
