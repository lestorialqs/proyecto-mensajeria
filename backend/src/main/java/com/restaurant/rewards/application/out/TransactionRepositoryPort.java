package com.restaurant.rewards.application.out;

import com.restaurant.rewards.domain.Transaction;
import java.util.List;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);
    List<Transaction> findAll();
    List<Transaction> findByCustomerId(String customerId);
    long count();
    List<Transaction> findRecent(int limit);
}
