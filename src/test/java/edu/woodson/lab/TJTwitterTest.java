package edu.woodson.lab;

import org.junit.jupiter.api.Test;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.Arrays;
import java.util.List;

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
    void fetchTweets() throws TwitterException {
        TJTwitter twitter = new TJTwitter(new AbstractTwitter() {
            @Override
            public ResponseList<Status> getUserTimeline(String screenName, Paging paging) throws TwitterException {
                return super.getUserTimeline(screenName, paging);
            }
        });

        String handle = "test";
        Paging paging = new Paging();
        List<Status> statuses = twitter.fetchTweets(paging, handle);
        assertIterableEquals(Arrays.asList("test0", "test1"), statuses);
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