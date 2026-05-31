package com.restaurant.rewards.infrastructure.persistence.repository;

import com.restaurant.rewards.infrastructure.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, String> {
}
