package com.restaurant.rewards.application.in;

import com.restaurant.rewards.domain.Transaction;

public interface RewardProcessingUseCase {
    void processTransactionForReward(Transaction transaction);
}
