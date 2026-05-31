package com.restaurant.rewards.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class RewardCalculatorServiceTest {

    private final RewardCalculatorService service = new RewardCalculatorService();

    @Test
    void testCalculateReward_below50() {
        Transaction tx = new Transaction();
        tx.setCustomerId("c1");
        tx.setAmount(new BigDecimal("40.00"));

        Reward reward = service.calculateReward(tx);

        assertEquals(40, reward.getPointsEarned());
        assertEquals(new BigDecimal("0.80"), reward.getCashbackEarned());
    }

    @Test
    void testCalculateReward_exactamente50() {
        Transaction tx = new Transaction();
        tx.setCustomerId("c1");
        tx.setAmount(new BigDecimal("50.00"));

        Reward reward = service.calculateReward(tx);

        assertEquals(50, reward.getPointsEarned());
        assertEquals(new BigDecimal("1.00"), reward.getCashbackEarned());
    }

    @Test
    void testCalculateReward_between50And150() {
        Transaction tx = new Transaction();
        tx.setCustomerId("c2");
        tx.setAmount(new BigDecimal("100.00"));

        Reward reward = service.calculateReward(tx);

        assertEquals(200, reward.getPointsEarned());
        assertEquals(new BigDecimal("3.00"), reward.getCashbackEarned());
    }

    @Test
    void testCalculateReward_exactamente150() {
        Transaction tx = new Transaction();
        tx.setCustomerId("c2");
        tx.setAmount(new BigDecimal("150.00"));

        Reward reward = service.calculateReward(tx);

        assertEquals(300, reward.getPointsEarned());
        assertEquals(new BigDecimal("4.50"), reward.getCashbackEarned());
    }

    @Test
    void testCalculateReward_above150() {
        Transaction tx = new Transaction();
        tx.setCustomerId("c3");
        tx.setAmount(new BigDecimal("200.00"));

        Reward reward = service.calculateReward(tx);

        assertEquals(600, reward.getPointsEarned());
        assertEquals(new BigDecimal("10.00"), reward.getCashbackEarned());
    }

    @Test
    void testCalculateReward_nullAmount_usaCero() {
        Transaction tx = new Transaction();
        tx.setCustomerId("c4");
        tx.setAmount(null);

        Reward reward = service.calculateReward(tx);

        assertEquals(0, reward.getPointsEarned());
        assertEquals(new BigDecimal("0.00"), reward.getCashbackEarned());
    }

    @Test
    void testCalculateReward_asignaCustomerIdYTransactionId() {
        Transaction tx = new Transaction();
        tx.setId("tx-123");
        tx.setCustomerId("cust-456");
        tx.setAmount(new BigDecimal("75.00"));

        Reward reward = service.calculateReward(tx);

        assertEquals("cust-456", reward.getCustomerId());
        assertEquals("tx-123", reward.getTransactionId());
        assertNotNull(reward.getId());
        assertNotNull(reward.getTimestamp());
    }
}
