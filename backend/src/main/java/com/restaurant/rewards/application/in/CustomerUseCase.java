package com.restaurant.rewards.application.in;

import com.restaurant.rewards.domain.Customer;
import java.util.List;

public interface CustomerUseCase {
    Customer createCustomer(Customer customer);
    Customer getCustomer(String id);
    List<Customer> getAllCustomers();
}
