package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.in.CustomerUseCase;
import com.restaurant.rewards.application.out.CustomerRepositoryPort;
import com.restaurant.rewards.domain.Customer;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements CustomerUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;

    public CustomerService(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customer.getId() == null || customer.getId().isEmpty()) {
            customer.setId(UUID.randomUUID().toString());
        }
        return customerRepositoryPort.save(customer);
    }

    @Override
    public Customer getCustomer(String id) {
        return customerRepositoryPort.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepositoryPort.findAll();
    }
}
