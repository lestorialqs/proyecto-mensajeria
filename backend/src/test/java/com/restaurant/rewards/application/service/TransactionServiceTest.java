package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.out.RewardNotificationPort;
import com.restaurant.rewards.application.out.TransactionRepositoryPort;
import com.restaurant.rewards.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    private TransactionRepositoryPort transactionRepositoryPort;
    private RewardNotificationPort rewardNotificationPort;
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionRepositoryPort = Mockito.mock(TransactionRepositoryPort.class);
        rewardNotificationPort = Mockito.mock(RewardNotificationPort.class);
        transactionService = new TransactionService(transactionRepositoryPort, rewardNotificationPort);
    }

    @Test
    void createTransaction_sinId_asignaUUID() {
        Transaction input = new Transaction();
        input.setAmount(new BigDecimal("50.00"));
        input.setCustomerId("cust-1");
        when(transactionRepositoryPort.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

        Transaction result = transactionService.createTransaction(input);

        assertNotNull(result.getId());
        assertFalse(result.getId().isEmpty());
    }

    @Test
    void createTransaction_sinTimestamp_asignaAhora() {
        Transaction input = new Transaction();
        input.setAmount(new BigDecimal("30.00"));
        input.setCustomerId("cust-2");
        when(transactionRepositoryPort.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

        Transaction result = transactionService.createTransaction(input);

        assertNotNull(result.getTimestamp());
    }

    @Test
    void createTransaction_conTimestampExistente_noLoCambia() {
        Transaction input = new Transaction();
        LocalDateTime fecha = LocalDateTime.of(2025, 1, 1, 10, 0);
        input.setTimestamp(fecha);
        input.setAmount(new BigDecimal("30.00"));
        when(transactionRepositoryPort.save(any(Transaction.class))).thenAnswer(inv -> inv.getArgument(0));

        Transaction result = transactionService.createTransaction(input);

        assertEquals(fecha, result.getTimestamp());
    }

    @Test
    void createTransaction_enviaEventoDeRecompensa() {
        Transaction input = new Transaction();
        input.setAmount(new BigDecimal("100.00"));
        input.setCustomerId("cust-3");
        Transaction saved = new Transaction();
        saved.setId("tx-saved");
        when(transactionRepositoryPort.save(any(Transaction.class))).thenReturn(saved);

        transactionService.createTransaction(input);

        verify(rewardNotificationPort, times(1)).sendTransactionEvent(saved);
    }

    @Test
    void getAllTransactions_retornaLista() {
        List<Transaction> lista = Arrays.asList(new Transaction(), new Transaction());
        when(transactionRepositoryPort.findAll()).thenReturn(lista);

        List<Transaction> result = transactionService.getAllTransactions();

        assertEquals(2, result.size());
    }

    @Test
    void getTransactionsByCustomerId_retornaListaFiltrada() {
        List<Transaction> lista = Arrays.asList(new Transaction());
        when(transactionRepositoryPort.findByCustomerId("cust-99")).thenReturn(lista);

        List<Transaction> result = transactionService.getTransactionsByCustomerId("cust-99");

        assertEquals(1, result.size());
    }
}
