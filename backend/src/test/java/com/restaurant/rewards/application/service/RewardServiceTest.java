package com.restaurant.rewards.application.service;

import com.restaurant.rewards.application.out.RewardRepositoryPort;
import com.restaurant.rewards.domain.Reward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RewardServiceTest {

    private RewardRepositoryPort rewardRepositoryPort;
    private RewardService rewardService;

    @BeforeEach
    void setUp() {
        rewardRepositoryPort = Mockito.mock(RewardRepositoryPort.class);
        rewardService = new RewardService(rewardRepositoryPort);
    }

    @Test
    void getAllRewards_retornaListaCompleta() {
        List<Reward> lista = Arrays.asList(new Reward(), new Reward(), new Reward());
        when(rewardRepositoryPort.findAll()).thenReturn(lista);

        List<Reward> result = rewardService.getAllRewards();

        assertEquals(3, result.size());
        verify(rewardRepositoryPort).findAll();
    }

    @Test
    void getRewardsByCustomerId_retornaListaFiltrada() {
        List<Reward> lista = Arrays.asList(new Reward());
        when(rewardRepositoryPort.findByCustomerId("cust-5")).thenReturn(lista);

        List<Reward> result = rewardService.getRewardsByCustomerId("cust-5");

        assertEquals(1, result.size());
        verify(rewardRepositoryPort).findByCustomerId("cust-5");
    }

    @Test
    void getAllRewards_sinRewards_retornaListaVacia() {
        when(rewardRepositoryPort.findAll()).thenReturn(Arrays.asList());

        List<Reward> result = rewardService.getAllRewards();

        assertTrue(result.isEmpty());
    }
}
