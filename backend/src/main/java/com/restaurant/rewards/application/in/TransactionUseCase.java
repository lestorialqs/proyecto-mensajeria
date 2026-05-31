package com.restaurant.rewards.application.in;

import com.restaurant.rewards.domain.Transaction;
import java.util.List;

public interface TransactionUseCase {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByCustomerId(String customerId);
}
