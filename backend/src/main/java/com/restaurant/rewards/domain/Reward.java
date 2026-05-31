package com.restaurant.rewards.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reward {
    private String id;
    private String customerId;
    private String transactionId;
    private int pointsEarned;
    private BigDecimal cashbackEarned;
    private LocalDateTime timestamp;

    public Reward() {
        // Constructor sin argumentos requerido por el serializador JSON (Jackson)
    }

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
