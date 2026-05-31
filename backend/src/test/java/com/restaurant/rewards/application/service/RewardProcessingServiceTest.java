package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.out.CustomerRepositoryPort;
import com.restaurant.rewards.application.out.RewardNotificationPort;
import com.restaurant.rewards.application.out.RewardRepositoryPort;
import com.restaurant.rewards.domain.Customer;
import com.restaurant.rewards.domain.Reward;
import com.restaurant.rewards.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RewardProcessingServiceTest {

    private RewardRepositoryPort rewardRepositoryPort;
    private RewardNotificationPort rewardNotificationPort;
    private CustomerRepositoryPort customerRepositoryPort;
    private RewardProcessingService service;

    @BeforeEach
    void setUp() {
        rewardRepositoryPort = Mockito.mock(RewardRepositoryPort.class);
        rewardNotificationPort = Mockito.mock(RewardNotificationPort.class);
        customerRepositoryPort = Mockito.mock(CustomerRepositoryPort.class);
        service = new RewardProcessingService(rewardRepositoryPort, rewardNotificationPort, customerRepositoryPort);
    }

    @Test
    void processTransaction_calculaYGuardaReward() {
        Transaction tx = new Transaction();
        tx.setId("tx-1");
        tx.setCustomerId("cust-1");
        tx.setAmount(new BigDecimal("100.00"));

        when(rewardRepositoryPort.save(any(Reward.class))).thenReturn(new Reward());
        when(customerRepositoryPort.findById("cust-1")).thenReturn(Optional.empty());

        service.processTransactionForReward(tx);

        verify(rewardRepositoryPort, times(1)).save(any(Reward.class));
        verify(rewardNotificationPort, times(1)).sendRewardNotification(any(Reward.class));
    }

    @Test
    void processTransaction_actualizaTotalesDelCliente() {
        Transaction tx = new Transaction();
        tx.setId("tx-2");
        tx.setCustomerId("cust-2");
        tx.setAmount(new BigDecimal("200.00")); // above 150 → 3x points = 600, 5% cashback = 10.00

        Customer customer = new Customer("cust-2", "Henry", "h@h.com", "111", "999");
        customer.setTotalPoints(100);
        customer.setTotalCashback(5.0);

        // savedReward no se usa directamente — el servicio calcula internamente el reward
        when(rewardRepositoryPort.save(any(Reward.class))).thenAnswer(inv -> inv.getArgument(0));
        when(customerRepositoryPort.findById("cust-2")).thenReturn(Optional.of(customer));

        service.processTransactionForReward(tx);

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepositoryPort).save(captor.capture());
        Customer updatedCustomer = captor.getValue();

        assertEquals(700, updatedCustomer.getTotalPoints()); // 100 + 600
        assertTrue(updatedCustomer.getTotalCashback() > 5.0); // 5.0 + 10.0 = 15.0
    }

    @Test
    void processTransaction_clienteNoExiste_noLanzaError() {
        Transaction tx = new Transaction();
        tx.setId("tx-3");
        tx.setCustomerId("cust-no-existe");
        tx.setAmount(new BigDecimal("50.00"));

        when(rewardRepositoryPort.save(any(Reward.class))).thenAnswer(inv -> inv.getArgument(0));
        when(customerRepositoryPort.findById("cust-no-existe")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> service.processTransactionForReward(tx));
    }
}
