package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.in.TransactionUseCase;
import com.restaurant.rewards.application.out.RewardNotificationPort;
import com.restaurant.rewards.application.out.TransactionRepositoryPort;
import com.restaurant.rewards.domain.Transaction;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService implements TransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final RewardNotificationPort rewardNotificationPort;

    public TransactionService(TransactionRepositoryPort transactionRepositoryPort,
                              RewardNotificationPort rewardNotificationPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.rewardNotificationPort = rewardNotificationPort;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getId() == null || transaction.getId().isEmpty()) {
            transaction.setId(UUID.randomUUID().toString());
        }
        if (transaction.getTimestamp() == null) {
            transaction.setTimestamp(LocalDateTime.now());
        }
        Transaction savedTransaction = transactionRepositoryPort.save(transaction);
        rewardNotificationPort.sendTransactionEvent(savedTransaction);
        return savedTransaction;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepositoryPort.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByCustomerId(String customerId) {
        return transactionRepositoryPort.findByCustomerId(customerId);
    }
}
