package org.example.service;

import org.example.data.CustomerData;
import org.example.data.ResourceId;

public interface CustomerService {
    CustomerData getCustomer(Long id);
    ResourceId saveCustomer(CustomerData customer);
}
