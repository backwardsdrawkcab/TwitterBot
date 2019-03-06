package edu.woodson;

import edu.woodson.util.RandomSupplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import twitter4j.Status;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class TJTwitterStatisticsTest {
    private TJTwitterStatistics statistics;

    @BeforeEach
    public void beforeEach() {
        this.statistics = new TJTwitterStatistics();
    }

    @Test
    void calculateMax() {
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
    }

    @Test
    void setValues() {
        List<Status> statuses = new StatusSupplier().create(1, 10);
        List<String> words = Arrays.asList("test0", "test1");
        statistics.setValues(statuses, words);

        assertIterableEquals(statuses, statistics.statuses);
        assertIterableEquals(words, statistics.words);
    }

    @Test
    void sortAndRemoveEntries() {
    }

    private class StatusSupplier extends RandomSupplier<Status> {
        @Override
        public Status get() {
            return Mockito.mock(Status.class);
        }
    }
}