package com.example.stats;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StatsRepositoryTest {

    @Autowired
    private StatsRepository repository;

    @Test
    void findByStudentId_shouldReturnCorrectStats() {
        Stats s1 = new Stats(1L, "Math", 5.0, 2.0, 3.5);
        Stats s2 = new Stats(2L, "Math", 4.0, 3.0, 3.5);
        repository.save(s1);
        repository.save(s2);

        List<Stats> result = repository.findByStudentId(1L);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getStudentId());
    }

    @Test
    void findBySubject_shouldReturnCorrectStats() {
        Stats s1 = new Stats(1L, "Math", 5.0, 2.0, 3.5);
        Stats s2 = new Stats(2L, "Science", 4.0, 3.0, 3.5);
        repository.save(s1);
        repository.save(s2);

        List<Stats> result = repository.findBySubject("Math");
        assertEquals(1, result.size());
        assertEquals("Math", result.get(0).getSubject());
    }
}
