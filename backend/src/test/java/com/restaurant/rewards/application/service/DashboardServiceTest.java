package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.out.CustomerRepositoryPort;
import com.restaurant.rewards.application.out.RewardRepositoryPort;
import com.restaurant.rewards.application.out.TransactionRepositoryPort;
import com.restaurant.rewards.domain.DashboardStats;
import com.restaurant.rewards.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardServiceTest {

    private CustomerRepositoryPort customerRepositoryPort;
    private TransactionRepositoryPort transactionRepositoryPort;
    private RewardRepositoryPort rewardRepositoryPort;
    private DashboardService dashboardService;

    @BeforeEach
    void setUp() {
        customerRepositoryPort = Mockito.mock(CustomerRepositoryPort.class);
        transactionRepositoryPort = Mockito.mock(TransactionRepositoryPort.class);
        rewardRepositoryPort = Mockito.mock(RewardRepositoryPort.class);
        dashboardService = new DashboardService(customerRepositoryPort, transactionRepositoryPort, rewardRepositoryPort);
    }

    @Test
    void getStats_calculaTodosLosCamposCorrectamente() {
        when(customerRepositoryPort.count()).thenReturn(5L);
        when(transactionRepositoryPort.count()).thenReturn(20L);
        when(rewardRepositoryPort.count()).thenReturn(20L);
        when(rewardRepositoryPort.sumPoints()).thenReturn(1000L);
        when(rewardRepositoryPort.sumCashback()).thenReturn(new BigDecimal("50.00"));
        when(transactionRepositoryPort.findRecent(5)).thenReturn(Arrays.asList(new Transaction()));

        DashboardStats stats = dashboardService.getStats();

        assertEquals(5L, stats.getTotalCustomers());
        assertEquals(20L, stats.getTotalTransactions());
        assertEquals(20L, stats.getTotalRewards());
        assertEquals(1000L, stats.getTotalPointsAwarded());
        assertEquals(new BigDecimal("50.00"), stats.getTotalCashback());
        assertEquals(1, stats.getRecentTransactions().size());
    }

    @Test
    void getStats_cashbackNull_retornaCero() {
        when(customerRepositoryPort.count()).thenReturn(0L);
        when(transactionRepositoryPort.count()).thenReturn(0L);
        when(rewardRepositoryPort.count()).thenReturn(0L);
        when(rewardRepositoryPort.sumPoints()).thenReturn(0L);
        when(rewardRepositoryPort.sumCashback()).thenReturn(null);
        when(transactionRepositoryPort.findRecent(5)).thenReturn(Arrays.asList());

        DashboardStats stats = dashboardService.getStats();

        assertEquals(BigDecimal.ZERO, stats.getTotalCashback());
    }
}
