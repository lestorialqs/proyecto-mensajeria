package com.restaurant.rewards.application.out;

import com.restaurant.rewards.domain.Reward;
import com.restaurant.rewards.domain.Transaction;

public interface RewardNotificationPort {
    void sendTransactionEvent(Transaction transaction);
    void sendRewardNotification(Reward reward);
}
