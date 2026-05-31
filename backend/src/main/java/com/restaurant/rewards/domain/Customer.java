package com.restaurant.rewards.domain;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String cardNumber;
    private String phone;
    private Integer totalPoints = 0;
    private Double totalCashback = 0.0;

    public Customer() {}

    public Customer(String id, String name, String email, String cardNumber, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cardNumber = cardNumber;
        this.phone = phone;
    }

    public Integer getTotalPoints() { return totalPoints == null ? 0 : totalPoints; }
    public void setTotalPoints(Integer totalPoints) { this.totalPoints = totalPoints; }

    public Double getTotalCashback() { return totalCashback == null ? 0.0 : totalCashback; }
    public void setTotalCashback(Double totalCashback) { this.totalCashback = totalCashback; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
