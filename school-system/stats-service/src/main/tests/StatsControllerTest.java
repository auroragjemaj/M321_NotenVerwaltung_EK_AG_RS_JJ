package com.example.stats;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.TestPropertySource;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StatsController.class)
@TestPropertySource(properties = "spring.sql.init.mode=never")
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsService statsService;

    @Test
    void testGetAllStats() throws Exception {
        List<Stats> statsList = Arrays.asList(new Stats(), new Stats());
        when(statsService.getAllStats()).thenReturn(statsList);
        mockMvc.perform(get("/api/stats"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetStatByIdFound() throws Exception {
        Stats stat = new Stats();
        when(statsService.getStatById(1L)).thenReturn(Optional.of(stat));
        mockMvc.perform(get("/api/stats/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetStatByIdNotFound() throws Exception {
        when(statsService.getStatById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/stats/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testRefreshStats() throws Exception {
        List<Stats> refreshedStats = Arrays.asList(new Stats());
        when(statsService.refreshStats()).thenReturn(refreshedStats);
        mockMvc.perform(post("/api/stats/refresh"))
                .andExpect(status().isOk());
    }
}
