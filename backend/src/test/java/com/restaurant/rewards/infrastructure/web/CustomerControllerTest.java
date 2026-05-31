package com.restaurant.rewards.infrastructure.web;

import com.restaurant.rewards.application.in.CustomerUseCase;
import com.restaurant.rewards.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerUseCase customerUseCase;

    @Test
    void testRegisterCustomer() throws Exception {
        Customer c = new Customer("1", "John", "john@mail.com", "123", "999");
        when(customerUseCase.createCustomer(any())).thenReturn(c);

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\", \"email\":\"john@mail.com\", \"cardNumber\":\"123\", \"phone\":\"999\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllCustomers() throws Exception {
        when(customerUseCase.getAllCustomers()).thenReturn(List.of());
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer c = new Customer("1", "John", "john@mail.com", "123", "999");
        when(customerUseCase.getCustomer("1")).thenReturn(c);
        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk());

        when(customerUseCase.getCustomer("2")).thenReturn(null);
        mockMvc.perform(get("/api/customers/2"))
                .andExpect(status().isNotFound());
    }
}
