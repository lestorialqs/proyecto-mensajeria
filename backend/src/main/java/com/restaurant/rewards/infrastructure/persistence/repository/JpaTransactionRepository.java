package com.restaurant.rewards.infrastructure.persistence.repository;

import com.restaurant.rewards.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTransactionRepository extends JpaRepository<TransactionEntity, String> {
    List<TransactionEntity> findByCustomerId(String customerId);
    
    @Query("SELECT t FROM TransactionEntity t ORDER BY t.timestamp DESC")
    List<TransactionEntity> findRecentTransactions(Pageable pageable);
}
