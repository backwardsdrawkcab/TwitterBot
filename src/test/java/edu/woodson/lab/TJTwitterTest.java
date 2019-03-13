package edu.woodson.lab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @Test
    void getMostPopularWord() {
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