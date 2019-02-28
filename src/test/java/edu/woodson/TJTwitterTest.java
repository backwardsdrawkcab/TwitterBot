package edu.woodson;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import twitter4j.Twitter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TJTwitterTest {

    @Test
    void getTerms() {
    }

    @Test
    void getNumberOfTweets() {
    }

    @Test
    void getMostPopularWord() {
    }

    @Test
    void getFrequencyMax() {
    }

    @Test
    void tweetOut() {
    }

    @Test
    void queryHandle() {
    }

    @Test
    void removePunctuation() {
    }

    @Test
    void investigate() {
    }

    @Test
    void sampleInvestigate() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"I", "have", "a", "pen,", "an", "apple!"})
    void splitIntoWords(String word) {
        TJTwitter tjTwitter = new TJTwitter(Mockito.mock(Twitter.class));
        List<String> words = tjTwitter.splitIntoWords(Collections.singletonList("I have a pen, I have an apple!"));
        assertEquals(8, words.size());
        assertTrue(words.contains(word));
    }

    @Test
    void mostPopularWord() {
        TJTwitter twitter = new TJTwitter(Mockito.mock(Twitter.class));
        Set<String> strings = twitter.mostPopularWord(Arrays.asList("test0", "test", "test1", "test2"));

        assertEquals(1, strings.size());
        assertEquals("test1", CollectionUtil.toSingle(strings));
    }
}