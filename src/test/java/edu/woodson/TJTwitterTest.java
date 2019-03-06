package edu.woodson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import twitter4j.Twitter;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TJTwitterTest {
    private TJTwitter twitter;

    @BeforeEach
    void beforeEach() {
        twitter = new TJTwitter(Mockito.mock(Twitter.class));
    }

    @Test
    void calculateMax() {
        Map<String, Long> map = new HashMap<>();
        map.put("test0", 1L);
        map.put("test1", 2L);
        map.put("test2", 3L);
        Optional<Long> max = twitter.calculateMax(map);
        assertTrue(max.isPresent());
        assertEquals(3L, (long) max.get());
    }

    @Test
    void calculateMax1() {
    }

    @Test
    void calculateMax2() {
    }

    @Test
    void calculateMaxFrequency() {
    }

    @Test
    void createFrequencyMap() {
        Map<String, Long> map = twitter.createFrequencyMap(Arrays.asList("test0", "test1", "test1"));
        assertEquals(2, map.size());
        assertTrue(map.containsKey("test0"));
        assertTrue(map.containsKey("test1"));
        assertEquals(1L, (long) map.get("test0"));
        assertEquals(2L, (long) map.get("test1"));
    }

    @Test
    void createFrequencyMap1() {
    }

    @Test
    void fetchTweets() {
    }

    @Test
    void fetchTweets1() {
    }

    @Test
    void getCommonWordsURL() {
    }

    @Test
    void getFrequencyMax() {
    }

    @Test
    void getMaxFrequency() {
    }

    @Test
    void getMostPopularWord() {
    }

    @Test
    void getMostPopularWord1() {
    }

    @Test
    void getNumberOfTweets() {
    }

    @Test
    void getTerms() {
    }

    @Test
    void getWords() {
    }

    @Test
    void investigate() {
    }

    @Test
    void loadCommonWordsFromLocation() {
    }

    @Test
    void loadCommonWordsFromStream() {
    }

    @Test
    void mostPopularWord() {
        Set<String> strings = twitter.mostPopularWord(Arrays.asList("test0", "test1", "test1", "test2"));

        assertEquals(1, strings.size());
        assertEquals("test1", CollectionUtil.toSingle(strings));
    }

    @Test
    void mostPopularWord1() {
    }

    @Test
    void mostPopularWordToSingle() {
    }

    @Test
    void queryHandle() {
    }

    @Test
    void queryHandle1() {
    }

    @Test
    void removeCommonEnglishWords() {
    }

    @Test
    void removeEmptyStrings() {
    }

    @Test
    void removePunctuation() {
        TJTwitter tjTwitter = new TJTwitter(Mockito.mock(Twitter.class));
        //noinspection SpellCheckingInspection
        assertEquals("shouldremovepunctuationbutnot'", tjTwitter.removePunctuation("?Should,Remove,Punctuation!ButNot'"));
    }

    @Test
    void removePunctuation1() {
    }

    @Test
    void sampleInvestigate() {
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
    void sortAndRemoveEmpties1() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"I", "have", "a", "pen,", "an", "apple!"})
    void splitIntoWords(String word) {
        List<String> words = twitter.splitIntoWords(Collections.singletonList("I have a pen, I have an apple!"));
        assertEquals(8, words.size());
        assertTrue(words.contains(word));
    }

    @Test
    void splitIntoWords1() {
    }

    @Test
    void toMessage() {
    }

    @Test
    void tweetOut() {
    }

    @Test
    void tweetOut1() {
    }
}