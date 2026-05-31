package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.out.CustomerRepositoryPort;
import com.restaurant.rewards.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepositoryPort repositoryPort;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        repositoryPort = Mockito.mock(CustomerRepositoryPort.class);
        customerService = new CustomerService(repositoryPort);
    }

    @Test
    void createCustomer_sinId_asignaUUID() {
        Customer input = new Customer();
        input.setName("Henry Quispe");
        when(repositoryPort.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

        Customer result = customerService.createCustomer(input);

        assertNotNull(result.getId());
        assertFalse(result.getId().isEmpty());
        verify(repositoryPort).save(input);
    }

    @Test
    void createCustomer_conIdExistente_noLoCambia() {
        Customer input = new Customer();
        input.setId("mi-id-fijo");
        when(repositoryPort.save(any(Customer.class))).thenAnswer(inv -> inv.getArgument(0));

        Customer result = customerService.createCustomer(input);

        assertEquals("mi-id-fijo", result.getId());
    }

    @Test
    void getCustomer_existente_retornaCustomer() {
        Customer expected = new Customer("id1", "Ana", "ana@mail.com", "1111", "999");
        when(repositoryPort.findById("id1")).thenReturn(Optional.of(expected));

        Customer result = customerService.getCustomer("id1");

        assertNotNull(result);
        assertEquals("Ana", result.getName());
    }

    @Test
    void getCustomer_noExistente_retornaNull() {
        when(repositoryPort.findById("noexiste")).thenReturn(Optional.empty());

        Customer result = customerService.getCustomer("noexiste");

        assertNull(result);
    }

    @Test
    void getAllCustomers_retornaLista() {
        List<Customer> lista = Arrays.asList(
            new Customer("1", "A", "a@a.com", "1", "1"),
            new Customer("2", "B", "b@b.com", "2", "2")
        );
        when(repositoryPort.findAll()).thenReturn(lista);

        List<Customer> result = customerService.getAllCustomers();

        assertEquals(2, result.size());
    }
}
