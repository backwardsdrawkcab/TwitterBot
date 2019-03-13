package edu.woodson.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/13/2019
 */
class TJTwitterTest {
    @Test
    void getStatistics() {
        TJTwitter twitter = new TJTwitter(null);
        assertThrows(IllegalStateException.class, twitter::getStatistics);
    }

    @Test
    void getMaxFrequency() {
        TJTwitter twitter = new TJTwitter(null);
        twitter.statistics = new TJTwitterStatistics("test0", "test1", "test1");

        assertEquals(2, twitter.getMaxFrequency());
    }

    @Test
    void getMostPopularWord() {
        TJTwitter twitter = new TJTwitter(null);
        twitter.statistics = new TJTwitterStatistics("test0", "test1", "test1");

        assertEquals("test1", twitter.getMostPopularWord());
    }

    @Test
    void queryHandle() {
    }

    @Test
    void fetchTweets() {
    }

    @Test
    void splitIntoWords() {
    }

    @Test
    void toMessage() {
    }

    @Test
    void loadCommonWordsFromLocation() {
    }

    @Test
    void getCommonWordsURL() {
    }

    @Test
    void loadCommonWordsFromStream() {
    }

    @Test
    void removePunctuation() {
    }

    @Test
    void tweetOut() {
    }
}