package com.restaurant.rewards.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class RewardTest {

    @Test
    void testConstructorVacio() {
        Reward reward = new Reward();
        assertNull(reward.getId());
        assertNull(reward.getCustomerId());
        assertEquals(0, reward.getPointsEarned());
    }

    @Test
    void testSettersYGetters() {
        Reward reward = new Reward();
        LocalDateTime now = LocalDateTime.now();

        reward.setId("rew-001");
        reward.setCustomerId("cust-001");
        reward.setTransactionId("tx-001");
        reward.setPointsEarned(150);
        reward.setCashbackEarned(new BigDecimal("4.50"));
        reward.setTimestamp(now);

        assertEquals("rew-001", reward.getId());
        assertEquals("cust-001", reward.getCustomerId());
        assertEquals("tx-001", reward.getTransactionId());
        assertEquals(150, reward.getPointsEarned());
        assertEquals(new BigDecimal("4.50"), reward.getCashbackEarned());
        assertEquals(now, reward.getTimestamp());
    }
}
