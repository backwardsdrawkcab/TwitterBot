package edu.woodson.lab;

import edu.woodson.lab.TJTwitterStatistics;
import edu.woodson.util.RandomSupplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import twitter4j.Status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TJTwitterStatisticsTest {
    private TJTwitterStatistics statistics;

    @BeforeEach
    public void beforeEach() {
        this.statistics = new TJTwitterStatistics();
        this.statistics.setValues(Collections.emptyList(), Arrays.asList("Death", "awaits", "you", "!", "!", ""));
    }

    @Test
    void calculateMax() {
        assertEquals(2, statistics.calculateMax(statistics.createFrequencyMap()));
    }

    @Test
    void calculateMax1() {
    }

    @Test
    void createFrequencyMap() {
    }

    @Test
    void getMaxFrequency() {
    }

    @Test
    void getMostPopularWord() {
    }

    @Test
    void getStatuses() {
    }

    @Test
    void getWords() {
    }

    @Test
    void mostPopularWord() {
    }

    @Test
    void removeCommonWords() {

    }

    @Test
    void removeEmptyStrings() {
        assertTrue(statistics.removeEmptyStrings().isEmpty());
    }

    @Test
    void setValues() {
        List<Status> statuses = new StatusSupplier().create(1, 10);
        List<String> words = Arrays.asList("it", "attacks");
        statistics.setValues(statuses, words);

        assertIterableEquals(statuses, statistics.statuses);
        assertIterableEquals(words, statistics.words);
    }

    @Test
    void sortAndRemoveEntries() {
        List<String> words = statistics.sortAndRemoveEntries();
        assertIterableEquals(Arrays.asList("help", "me", "please"), words);
    }

    private class StatusSupplier extends RandomSupplier<Status> {
        @Override
        public Status get() {
            return Mockito.mock(Status.class);
        }
    }
}