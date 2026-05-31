package com.restaurant.rewards.domain;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testConstructorVacio() {
        Transaction tx = new Transaction();
        assertNull(tx.getId());
        assertNull(tx.getCustomerId());
        assertNull(tx.getAmount());
    }

    @Test
    void testSettersYGetters() {
        Transaction tx = new Transaction();
        LocalDateTime now = LocalDateTime.now();

        tx.setId("tx-001");
        tx.setCustomerId("cust-001");
        tx.setRestaurantCode("REST-A");
        tx.setAmount(new BigDecimal("75.50"));
        tx.setDescription("Cena especial");
        tx.setTimestamp(now);

        assertEquals("tx-001", tx.getId());
        assertEquals("cust-001", tx.getCustomerId());
        assertEquals("REST-A", tx.getRestaurantCode());
        assertEquals(new BigDecimal("75.50"), tx.getAmount());
        assertEquals("Cena especial", tx.getDescription());
        assertEquals(now, tx.getTimestamp());
    }
}
