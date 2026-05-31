package com.restaurant.rewards.infrastructure.persistence.adapter;

import com.restaurant.rewards.application.out.TransactionRepositoryPort;
import com.restaurant.rewards.domain.Transaction;
import com.restaurant.rewards.infrastructure.persistence.entity.TransactionEntity;
import com.restaurant.rewards.infrastructure.persistence.repository.JpaTransactionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionPersistenceAdapter implements TransactionRepositoryPort {

    private final JpaTransactionRepository repository;

    public TransactionPersistenceAdapter(JpaTransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = toEntity(transaction);
        TransactionEntity saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> findByCustomerId(String customerId) {
        return repository.findByCustomerId(customerId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<Transaction> findRecent(int limit) {
        return repository.findRecentTransactions(PageRequest.of(0, limit)).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private TransactionEntity toEntity(Transaction domain) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(domain.getId());
        entity.setCustomerId(domain.getCustomerId());
        entity.setRestaurantCode(domain.getRestaurantCode());
        entity.setAmount(domain.getAmount());
        entity.setDescription(domain.getDescription());
        entity.setTimestamp(domain.getTimestamp());
        return entity;
    }

    private Transaction toDomain(TransactionEntity entity) {
        Transaction domain = new Transaction();
        domain.setId(entity.getId());
        domain.setCustomerId(entity.getCustomerId());
        domain.setRestaurantCode(entity.getRestaurantCode());
        domain.setAmount(entity.getAmount());
        domain.setDescription(entity.getDescription());
        domain.setTimestamp(entity.getTimestamp());
        return domain;
    }
}
