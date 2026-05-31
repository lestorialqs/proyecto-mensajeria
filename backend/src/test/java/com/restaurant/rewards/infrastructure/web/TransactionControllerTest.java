package com.restaurant.rewards.infrastructure.web;

import com.restaurant.rewards.application.in.TransactionUseCase;
import com.restaurant.rewards.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionUseCase transactionUseCase;

    @Test
    void testProcessTransaction() throws Exception {
        when(transactionUseCase.createTransaction(any()))
                .thenReturn(new Transaction());

        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerId\":\"1\", \"restaurantCode\":\"REST01\", \"amount\":50.0, \"description\":\"Dinner\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllTransactions() throws Exception {
        when(transactionUseCase.getAllTransactions()).thenReturn(List.of());
        mockMvc.perform(get("/api/transactions"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetTransactionsByCustomer() throws Exception {
        when(transactionUseCase.getTransactionsByCustomerId("1")).thenReturn(List.of());
        mockMvc.perform(get("/api/transactions/customer/1"))
                .andExpect(status().isOk());
    }
}
