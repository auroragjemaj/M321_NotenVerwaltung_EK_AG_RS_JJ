package com.example.stats;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatsServiceTest {

    @Test
    void generateStats_shouldReturnCorrectStats() {
        // Mock RestTemplate
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        StatsService service = new StatsService(restTemplate);

        Map<String, Object>[] mockGrades = new Map[]{
            Map.of("studentId", 1, "subject", "Math", "value", 5),
            Map.of("studentId", 1, "subject", "Math", "value", 3)
        };

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Map[].class)))
                .thenReturn(mockGrades);

        List<Stats> statsList = service.generateStats();

        assertEquals(1, statsList.size());
        Stats stats = statsList.get(0);
        assertEquals(1L, stats.getStudentId());
        assertEquals("Math", stats.getSubject());
        assertEquals(5.0, stats.getHighestGrade());
        assertEquals(3.0, stats.getLowestGrade());
        assertEquals(4.0, stats.getAverageGrade());
    }
}
