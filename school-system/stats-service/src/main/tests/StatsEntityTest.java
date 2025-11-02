package com.example.stats;

import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StatsEntityTest {

    @Test
    void testGettersAndSetters() {
        Stats stat = new Stats();
        stat.setId(1L);
        stat.setStudentId(10L);
        stat.setSubject("Mathematik");
        stat.setHighestGrade(1.0);
        stat.setLowestGrade(5.0);
        stat.setAverageGrade(3.0);

        assertEquals(1L, stat.getId());
        assertEquals(10L, stat.getStudentId());
        assertEquals("Mathematik", stat.getSubject());
        assertEquals(1.0, stat.getHighestGrade());
        assertEquals(5.0, stat.getLowestGrade());
        assertEquals(3.0, stat.getAverageGrade());
    }
}
