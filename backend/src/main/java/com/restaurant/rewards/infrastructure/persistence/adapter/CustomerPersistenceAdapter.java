package com.restaurant.rewards.infrastructure.persistence.adapter;

import com.restaurant.rewards.application.out.CustomerRepositoryPort;
import com.restaurant.rewards.domain.Customer;
import com.restaurant.rewards.infrastructure.persistence.entity.CustomerEntity;
import com.restaurant.rewards.infrastructure.persistence.repository.JpaCustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerPersistenceAdapter implements CustomerRepositoryPort {

    private final JpaCustomerRepository repository;

    public CustomerPersistenceAdapter(JpaCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = toEntity(customer);
        CustomerEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return repository.count();
    }

    private CustomerEntity toEntity(Customer domain) {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setCardNumber(domain.getCardNumber());
        entity.setPhone(domain.getPhone());
        entity.setTotalPoints(domain.getTotalPoints());
        entity.setTotalCashback(domain.getTotalCashback());
        return entity;
    }

    private Customer toDomain(CustomerEntity entity) {
        Customer domain = new Customer();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setEmail(entity.getEmail());
        domain.setCardNumber(entity.getCardNumber());
        domain.setPhone(entity.getPhone());
        domain.setTotalPoints(entity.getTotalPoints());
        domain.setTotalCashback(entity.getTotalCashback());
        return domain;
    }
}
