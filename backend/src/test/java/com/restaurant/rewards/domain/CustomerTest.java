package com.restaurant.rewards.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testConstructorVacio() {
        Customer customer = new Customer();
        assertNull(customer.getId());
        assertNull(customer.getName());
    }

    @Test
    void testConstructorConParametros() {
        Customer customer = new Customer("id1", "Juan", "juan@mail.com", "1234", "999");
        assertEquals("id1", customer.getId());
        assertEquals("Juan", customer.getName());
        assertEquals("juan@mail.com", customer.getEmail());
        assertEquals("1234", customer.getCardNumber());
        assertEquals("999", customer.getPhone());
    }

    @Test
    void testGetTotalPoints_valorNormal() {
        Customer customer = new Customer();
        customer.setTotalPoints(100);
        assertEquals(100, customer.getTotalPoints());
    }

    @Test
    void testGetTotalPoints_cuandoEsNull_retornaCero() {
        Customer customer = new Customer();
        customer.setTotalPoints(null);
        assertEquals(0, customer.getTotalPoints());
    }

    @Test
    void testGetTotalCashback_valorNormal() {
        Customer customer = new Customer();
        customer.setTotalCashback(15.50);
        assertEquals(15.50, customer.getTotalCashback());
    }

    @Test
    void testGetTotalCashback_cuandoEsNull_retornaCero() {
        Customer customer = new Customer();
        customer.setTotalCashback(null);
        assertEquals(0.0, customer.getTotalCashback());
    }

    @Test
    void testSettersYGetters() {
        Customer customer = new Customer();
        customer.setId("abc");
        customer.setName("Maria");
        customer.setEmail("maria@test.com");
        customer.setCardNumber("9999");
        customer.setPhone("111");

        assertEquals("abc", customer.getId());
        assertEquals("Maria", customer.getName());
        assertEquals("maria@test.com", customer.getEmail());
        assertEquals("9999", customer.getCardNumber());
        assertEquals("111", customer.getPhone());
    }
}
