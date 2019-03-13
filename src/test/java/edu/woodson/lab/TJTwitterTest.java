package edu.woodson.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import twitter4j.Twitter;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/13/2019
 */
class TJTwitterTest {
    private TJTwitter twitter;

    @BeforeEach
    void beforeEach(){
        this.twitter = new TJTwitter(Mockito.mock(Twitter.class));
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