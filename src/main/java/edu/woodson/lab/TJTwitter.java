package edu.woodson.lab;

import twitter4j.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class TJTwitter {
    private static final String COMMON_WORDS_LOCATION = "/commonWords.txt";
    private final Twitter twitter;

    private TJTwitterStatistics statistics;

    public TJTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public long getMaxFrequency() {
        return statistics.getMaxFrequency();
    }

    /******************  Part III - Tweet *******************/

    public String getMostPopularWord() {
        return statistics.getMostPopularWord();
    }

    public TJTwitterStatistics getStatistics() {
        if (statistics == null) {
            throw new IllegalStateException("Statistics have not been found because nothing was queried yet.");
        }

        return statistics;
    }

    /**
     * This method queries the tweets of a particular user's handle.
     *
     * @param handle the Twitter handle (username) without the @sign
     */
    @SuppressWarnings("unchecked")
    public void queryHandle(String handle) throws IOException {
        List<Status> statuses = fetchTweets(handle);
        List<String> words = splitIntoWords(toMessage(statuses));
        TJTwitterStatistics statistics = new TJTwitterStatistics();
        statistics.setValues(words);

        statistics.removeCommonWords(loadCommonWordsFromLocation());
        statistics.sortAndRemoveEntries();

        this.statistics = statistics;
    }

    /**
     * This method fetches the most recent 2,000 tweets of a particular user's handle and
     * stores them in an arrayList of Status objects.  Populates statuses.
     *
     * @param handle the Twitter handle (username) without the @sign
     */
    public List<Status> fetchTweets(String handle) {
        Paging paging = new Paging(1, 200);
        return IntStream.range(1, 11)
                .peek(paging::setPage)
                .mapToObj((IntFunction<Optional<ResponseList<Status>>>) value -> {
                    try {
                        return Optional.of(twitter.getUserTimeline(handle, paging));
                    } catch (TwitterException e) {
                        e.printStackTrace();
                        return Optional.empty();
                    }
                })
                .flatMap(Optional::stream)
                .flatMap((Function<ResponseList<Status>, Stream<Status>>) Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * This method takes each status and splits them into individual words.
     * Store the word in words.
     */
    public List<String> splitIntoWords(List<String> statuses) {
        return statuses.stream()
                .map(StringTokenizer::new)
                .map(Enumeration::asIterator)
                .map(objectIterator -> (Iterable<Object>) () -> objectIterator)
                .map(Iterable::spliterator)
                .flatMap(objectSpliterator -> StreamSupport.stream(objectSpliterator, false))
                .map(Object::toString)
                .collect(Collectors.toList());

    }

    List<String> toMessage(List<Status> statuses) {
        return statuses.stream()
                .map(Status::getText)
                .collect(Collectors.toList());
    }

    List<String> loadCommonWordsFromLocation() throws IOException {
        Optional<URL> commonWordsURL = getCommonWordsURL();
        if (!commonWordsURL.isPresent()) {
            throw new IllegalArgumentException("Could not find common words " + COMMON_WORDS_LOCATION);
        }

        return loadCommonWordsFromStream(commonWordsURL.get().openStream());
    }


    Optional<URL> getCommonWordsURL() {
        return Optional.ofNullable(getClass().getResource(TJTwitter.COMMON_WORDS_LOCATION));
    }

    List<String> loadCommonWordsFromStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        reader.close();
        return reader.lines()
                .map(String::trim)
                .map(s -> s.split(" "))
                .filter(strings -> strings.length == 1)
                .map(strings -> strings[0])
                .collect(Collectors.toList());
    }


    /**
     * This method removes common punctuation from each individual word.
     * This method changes everything to lower case.
     * Consider reusing code you wrote for a previous lab.
     * Consider if you want to remove the # or @ from your words. Could be interesting to keep (or remove).
     *
     * @ param String  the word you wish to remove punctuation from
     * @ return String the word without any punctuation, all lower case
     */
    public String removePunctuation(String s) {
        return s.replaceAll("[^a-z'A-Z]", "").toLowerCase();
    }

    /**
     * This method tweets a given message.
     *
     * @param message a message you wish to Tweet out
     */
    public void tweetOut(String message) throws TwitterException {
        twitter.updateStatus(message);
    }
}
