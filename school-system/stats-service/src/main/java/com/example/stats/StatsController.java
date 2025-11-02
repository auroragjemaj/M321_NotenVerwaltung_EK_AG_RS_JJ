package com.example.stats;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StatsRepository repository;

    @Test
    void getAllStats_shouldReturnStats() throws Exception {
        repository.save(new Stats(1L, "Math", 5.0, 2.0, 3.5));

        mockMvc.perform(get("/api/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentId").value(1))
                .andExpect(jsonPath("$[0].subject").value("Math"));
    }

    @Test
    void getStatsByStudent_shouldReturnFiltered() throws Exception {
        repository.save(new Stats(1L, "Math", 5.0, 2.0, 3.5));
        repository.save(new Stats(2L, "Science", 4.0, 3.0, 3.5));

        mockMvc.perform(get("/api/stats/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].studentId").value(1));
    }
}
