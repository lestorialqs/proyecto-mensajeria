package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.in.DashboardUseCase;
import com.restaurant.rewards.application.out.CustomerRepositoryPort;
import com.restaurant.rewards.application.out.RewardRepositoryPort;
import com.restaurant.rewards.application.out.TransactionRepositoryPort;
import com.restaurant.rewards.domain.DashboardStats;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class DashboardService implements DashboardUseCase {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final TransactionRepositoryPort transactionRepositoryPort;
    private final RewardRepositoryPort rewardRepositoryPort;

    public DashboardService(CustomerRepositoryPort customerRepositoryPort,
                            TransactionRepositoryPort transactionRepositoryPort,
                            RewardRepositoryPort rewardRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.transactionRepositoryPort = transactionRepositoryPort;
        this.rewardRepositoryPort = rewardRepositoryPort;
    }

    @Override
    public DashboardStats getStats() {
        DashboardStats stats = new DashboardStats();
        stats.setTotalCustomers(customerRepositoryPort.count());
        stats.setTotalTransactions(transactionRepositoryPort.count());
        stats.setTotalRewards(rewardRepositoryPort.count());
        stats.setTotalPointsAwarded(rewardRepositoryPort.sumPoints());
        
        BigDecimal totalCashback = rewardRepositoryPort.sumCashback();
        stats.setTotalCashback(totalCashback != null ? totalCashback : BigDecimal.ZERO);
        
        stats.setRecentTransactions(transactionRepositoryPort.findRecent(5));
        
        return stats;
    }
}
