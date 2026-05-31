package com.restaurant.rewards.domain;

import java.math.BigDecimal;
import java.util.List;

public class DashboardStats {
    private long totalCustomers;
    private long totalTransactions;
    private long totalRewards;
    private long totalPointsAwarded;
    private BigDecimal totalCashback;
    private List<Transaction> recentTransactions;

    public DashboardStats() {
        // Constructor sin argumentos requerido por el serializador JSON (Jackson)
    }

    public long getTotalCustomers() { return totalCustomers; }
    public void setTotalCustomers(long totalCustomers) { this.totalCustomers = totalCustomers; }

    public long getTotalTransactions() { return totalTransactions; }
    public void setTotalTransactions(long totalTransactions) { this.totalTransactions = totalTransactions; }

    public long getTotalRewards() { return totalRewards; }
    public void setTotalRewards(long totalRewards) { this.totalRewards = totalRewards; }

    public long getTotalPointsAwarded() { return totalPointsAwarded; }
    public void setTotalPointsAwarded(long totalPointsAwarded) { this.totalPointsAwarded = totalPointsAwarded; }

    public BigDecimal getTotalCashback() { return totalCashback; }
    public void setTotalCashback(BigDecimal totalCashback) { this.totalCashback = totalCashback; }

    public List<Transaction> getRecentTransactions() { return recentTransactions; }
    public void setRecentTransactions(List<Transaction> recentTransactions) { this.recentTransactions = recentTransactions; }
}
