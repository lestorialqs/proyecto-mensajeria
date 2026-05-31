package com.restaurant.rewards.infrastructure.web;

import com.restaurant.rewards.application.in.DashboardUseCase;
import com.restaurant.rewards.domain.DashboardStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DashboardController.class)
public class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DashboardUseCase dashboardUseCase;

    @Test
    void testGetDashboardStats() throws Exception {
        DashboardStats stats = new DashboardStats();
        when(dashboardUseCase.getStats()).thenReturn(stats);

        mockMvc.perform(get("/api/dashboard/stats"))
                .andExpect(status().isOk());
    }
}
