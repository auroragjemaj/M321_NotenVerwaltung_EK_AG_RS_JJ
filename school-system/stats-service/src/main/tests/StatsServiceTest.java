package com.example.stats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatsServiceTest {

    @Mock
    private StatsRepository statsRepository;

    @InjectMocks
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStats() {
        List<Stats> statsList = Arrays.asList(new Stats(), new Stats());
        when(statsRepository.findAll()).thenReturn(statsList);
        assertEquals(statsList, statsService.getAllStats());
    }

    @Test
    void testGetStatByIdFound() {
        Stats stat = new Stats();
        when(statsRepository.findById(1L)).thenReturn(Optional.of(stat));
        assertTrue(statsService.getStatById(1L).isPresent());
    }

    @Test
    void testGetStatByIdNotFound() {
        when(statsRepository.findById(1L)).thenReturn(Optional.empty());
        assertFalse(statsService.getStatById(1L).isPresent());
    }

    @Test
    void testRefreshStats() {
        Stats stat1 = new Stats();
        Stats stat2 = new Stats();
        List<Stats> statsList = Arrays.asList(stat1, stat2);
        when(statsRepository.findAll()).thenReturn(statsList);
        when(statsRepository.saveAll(anyList())).thenReturn(statsList);

        List<Stats> result = statsService.refreshStats();
        assertEquals(2, result.size());
    }
}
