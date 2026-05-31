package com.restaurant.rewards.infrastructure.web;

import com.restaurant.rewards.application.in.RewardUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardUseCase rewardUseCase;

    @Test
    void testGetAllRewards() throws Exception {
        when(rewardUseCase.getAllRewards()).thenReturn(List.of());
        mockMvc.perform(get("/api/rewards"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetRewardsByCustomer() throws Exception {
        when(rewardUseCase.getRewardsByCustomerId("1")).thenReturn(List.of());
        mockMvc.perform(get("/api/rewards/customer/1"))
                .andExpect(status().isOk());
    }
}
