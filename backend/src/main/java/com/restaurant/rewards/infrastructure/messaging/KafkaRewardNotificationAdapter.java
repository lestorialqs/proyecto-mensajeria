package com.restaurant.rewards.infrastructure.messaging;

import com.restaurant.rewards.application.in.RewardProcessingUseCase;
import com.restaurant.rewards.application.out.RewardNotificationPort;
import com.restaurant.rewards.domain.Reward;
import com.restaurant.rewards.domain.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import org.springframework.context.annotation.Lazy;

@Component
public class KafkaRewardNotificationAdapter implements RewardNotificationPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final RewardProcessingUseCase rewardProcessingUseCase;

    public KafkaRewardNotificationAdapter(KafkaTemplate<String, Object> kafkaTemplate,
                                          @Lazy RewardProcessingUseCase rewardProcessingUseCase) {
        this.kafkaTemplate = kafkaTemplate;
        this.rewardProcessingUseCase = rewardProcessingUseCase;
    }

    @Override
    public void sendTransactionEvent(Transaction transaction) {
        kafkaTemplate.send("dinner-transactions", transaction.getId(), transaction);
    }

    @Override
    public void sendRewardNotification(Reward reward) {
        kafkaTemplate.send("reward-notifications", reward.getId(), reward);
    }

    @KafkaListener(topics = "dinner-transactions", groupId = "rewards-group")
    public void listenTransaction(Transaction transaction) {
        rewardProcessingUseCase.processTransactionForReward(transaction);
    }
}
