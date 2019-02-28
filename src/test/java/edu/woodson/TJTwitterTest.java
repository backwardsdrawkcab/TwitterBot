package edu.woodson;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import twitter4j.Twitter;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        TJTwitter tjTwitter = new TJTwitter(Mockito.mock(Twitter.class));
        assertEquals("shouldremovepunctuationbutnot'", tjTwitter.removePunctuation("?Should,Remove,Punctuation!ButNot'"));
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
    void fetchTweets() {
    }

    @Test
    void removeCommonEnglishWords() {
    }

    @Test
    void sortAndRemoveEmpties() {
        TJTwitter tjTwitter = new TJTwitter(Mockito.mock(Twitter.class));

        List<String> input = new LinkedList<>();
        Collections.addAll(input, "hi", "", "I");

        List<String> expected = new LinkedList<>();
        Collections.addAll(expected, "I", "hi");

        assertEquals(expected, tjTwitter.sortAndRemoveEmpties(input));
    }

    @Test
    void mostPopularWord() {
    }
}