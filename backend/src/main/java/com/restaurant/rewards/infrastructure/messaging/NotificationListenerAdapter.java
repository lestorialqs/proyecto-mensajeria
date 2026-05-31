package com.restaurant.rewards.infrastructure.messaging;

import com.restaurant.rewards.application.out.CustomerRepositoryPort;
import com.restaurant.rewards.domain.Reward;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NotificationListenerAdapter.class);
    
    private final EmailService emailService;
    private final CustomerRepositoryPort customerRepositoryPort;

    public NotificationListenerAdapter(EmailService emailService, CustomerRepositoryPort customerRepositoryPort) {
        this.emailService = emailService;
        this.customerRepositoryPort = customerRepositoryPort;
    }

    @KafkaListener(topics = "reward-notifications", groupId = "notifications-group")
    public void listenRewardNotification(Reward reward) {
        logger.info("📩 [KAFKA] - Recibido evento para enviar email al cliente: {}", reward.getCustomerId());
        
        customerRepositoryPort.findById(reward.getCustomerId()).ifPresentOrElse(customer -> {
            try {
                emailService.sendRewardEmail(customer.getEmail(), customer.getName(), reward.getPointsEarned(), reward.getCashbackEarned().doubleValue());
                logger.info("✅ [ÉXITO] - Correo electrónico real enviado a: {}", customer.getEmail());
            } catch (Exception e) {
                logger.error("❌ [ERROR] - Falló el envío de correo a {}: {}", customer.getEmail(), e.getMessage());
            }
        }, () -> logger.warn("⚠️ No se encontró al cliente para enviar el correo."));
    }
}
