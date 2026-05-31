package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.in.RewardProcessingUseCase;
import com.restaurant.rewards.application.out.RewardNotificationPort;
import com.restaurant.rewards.application.out.RewardRepositoryPort;
import com.restaurant.rewards.domain.Reward;
import com.restaurant.rewards.domain.RewardCalculatorService;
import com.restaurant.rewards.domain.Transaction;
import org.springframework.stereotype.Service;

import com.restaurant.rewards.application.out.CustomerRepositoryPort;
import com.restaurant.rewards.domain.Customer;

@Service
public class RewardProcessingService implements RewardProcessingUseCase {

    private final RewardCalculatorService rewardCalculatorService;
    private final RewardRepositoryPort rewardRepositoryPort;
    private final RewardNotificationPort rewardNotificationPort;
    private final CustomerRepositoryPort customerRepositoryPort;

    public RewardProcessingService(RewardRepositoryPort rewardRepositoryPort,
                                   RewardNotificationPort rewardNotificationPort,
                                   CustomerRepositoryPort customerRepositoryPort) {
        this.rewardCalculatorService = new RewardCalculatorService();
        this.rewardRepositoryPort = rewardRepositoryPort;
        this.rewardNotificationPort = rewardNotificationPort;
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @Override
    public void processTransactionForReward(Transaction transaction) {
        Reward reward = rewardCalculatorService.calculateReward(transaction);
        Reward savedReward = rewardRepositoryPort.save(reward);
        
        // Update customer totals
        customerRepositoryPort.findById(transaction.getCustomerId()).ifPresent(customer -> {
            customer.setTotalPoints(customer.getTotalPoints() + reward.getPointsEarned());
            customer.setTotalCashback(customer.getTotalCashback() + reward.getCashbackEarned().doubleValue());
            customerRepositoryPort.save(customer);
        });

        rewardNotificationPort.sendRewardNotification(savedReward);
    }
}
