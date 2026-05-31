package com.restaurant.rewards.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rewards")
public class RewardEntity {
    @Id
    private String id;
    private String customerId;
    private String transactionId;
    private int pointsEarned;
    private BigDecimal cashbackEarned;
    private LocalDateTime timestamp;

    public RewardEntity() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public int getPointsEarned() { return pointsEarned; }
    public void setPointsEarned(int pointsEarned) { this.pointsEarned = pointsEarned; }

    public BigDecimal getCashbackEarned() { return cashbackEarned; }
    public void setCashbackEarned(BigDecimal cashbackEarned) { this.cashbackEarned = cashbackEarned; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
