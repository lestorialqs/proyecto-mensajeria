package com.restaurant.rewards.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DashboardStatsTest {

    @Test
    void testConstructorVacio() {
        DashboardStats stats = new DashboardStats();
        assertEquals(0L, stats.getTotalCustomers());
        assertEquals(0L, stats.getTotalTransactions());
        assertNull(stats.getTotalCashback());
    }

    @Test
    void testSettersYGetters() {
        DashboardStats stats = new DashboardStats();
        List<Transaction> txList = Arrays.asList(new Transaction());

        stats.setTotalCustomers(10L);
        stats.setTotalTransactions(25L);
        stats.setTotalRewards(25L);
        stats.setTotalPointsAwarded(5000L);
        stats.setTotalCashback(new BigDecimal("150.75"));
        stats.setRecentTransactions(txList);

        assertEquals(10L, stats.getTotalCustomers());
        assertEquals(25L, stats.getTotalTransactions());
        assertEquals(25L, stats.getTotalRewards());
        assertEquals(5000L, stats.getTotalPointsAwarded());
        assertEquals(new BigDecimal("150.75"), stats.getTotalCashback());
        assertEquals(1, stats.getRecentTransactions().size());
    }
}
