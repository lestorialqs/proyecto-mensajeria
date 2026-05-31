package com.restaurant.rewards.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String customerId;
    private String restaurantCode;
    private BigDecimal amount;
    private String description;
    private LocalDateTime timestamp;

    public Transaction() {
        // Constructor sin argumentos requerido por JPA y el serializador JSON
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getRestaurantCode() { return restaurantCode; }
    public void setRestaurantCode(String restaurantCode) { this.restaurantCode = restaurantCode; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
