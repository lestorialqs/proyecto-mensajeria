package com.restaurant.rewards.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic dinnerTransactionsTopic() {
        return TopicBuilder.name("dinner-transactions")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic rewardNotificationsTopic() {
        return TopicBuilder.name("reward-notifications")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
