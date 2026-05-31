package com.restaurant.rewards.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

public class RewardCalculatorService {
    
    public Reward calculateReward(Transaction transaction) {
        BigDecimal amount = transaction.getAmount();
        int points = 0;
        BigDecimal cashback = BigDecimal.ZERO;

        if (amount == null) {
            amount = BigDecimal.ZERO;
        }

        if (amount.compareTo(new BigDecimal("50")) <= 0) {
            points = amount.intValue();
            cashback = amount.multiply(new BigDecimal("0.02"));
        } else if (amount.compareTo(new BigDecimal("150")) <= 0) {
            points = amount.intValue() * 2;
            cashback = amount.multiply(new BigDecimal("0.03"));
        } else {
            points = amount.intValue() * 3;
            cashback = amount.multiply(new BigDecimal("0.05"));
        }

        cashback = cashback.setScale(2, RoundingMode.HALF_UP);

        Reward reward = new Reward();
        reward.setId(UUID.randomUUID().toString());
        reward.setCustomerId(transaction.getCustomerId());
        reward.setTransactionId(transaction.getId());
        reward.setPointsEarned(points);
        reward.setCashbackEarned(cashback);
        reward.setTimestamp(LocalDateTime.now());
        
        return reward;
    }
}
