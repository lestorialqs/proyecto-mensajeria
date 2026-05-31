package com.restaurant.rewards.application.out;

import com.restaurant.rewards.domain.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);
    Optional<Customer> findById(String id);
    List<Customer> findAll();
    long count();
}
