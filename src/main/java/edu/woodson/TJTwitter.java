package edu.woodson;

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
    private final List<Status> statuses = new ArrayList<>();
    private final List<String> words = new ArrayList<>();
    private final Twitter twitter;

    public TJTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public int getMaxFrequency() {
        return calculateMaxFrequency();
    }

    int calculateMaxFrequency() {
        return Math.toIntExact(calculateMax(createFrequencyMap(words)).orElseThrow());
    }

    /******************  Part III - Tweet *******************/

    public String getMostPopularWord() {
        return mostPopularWordToSingle();
    }

    String mostPopularWordToSingle() {
        return CollectionUtil.toSingle(mostPopularWord(words));
    }

    /**
     * This method calculates the word that appears the most times
     * Consider case - should it be case sensitive?  The choice is yours.
     * <p>
     * post will populate the maxFrequency variable with the frequency of the most common word
     *
     * @param words The words to search.
     * @post will populate the maxFrequency variable with the frequency of the most common word
     */
    @SuppressWarnings("unchecked")
    public Set<String> mostPopularWord(List<String> words) {
        if (words.isEmpty()) {
            throw new IllegalArgumentException("No words found for " + words);
        }

        Map<String, Long> map = createFrequencyMap(words);
        long max = calculateMax(words, map);
        return map.keySet()
                .stream()
                .filter(s -> map.get(s).equals(max))
                .collect(Collectors.toSet());
    }

    /******************  Part III - Test *******************/

    Map<String, Long> createFrequencyMap(List<String> words) {
        return words.stream().collect(Collectors.toMap(Function.identity(), string -> words.stream()
                .filter(s -> s.equals(string))
                .count(), Long::max));
    }

    long calculateMax(List<String> words, Map<String, Long> map) {
        Optional<Long> maxOptional = calculateMax(map);
        if (!maxOptional.isPresent()) {
            throw new IllegalArgumentException("No maximum found for " + words);
        }

        return maxOptional.get();
    }

    Optional<Long> calculateMax(Map<String, Long> map) {
        return map.values().stream().max(Long::compareTo);
    }

    /******************  Part IV *******************/
    public void investigate() {
        //Enter your code here
        //TODO: put some code here
    }

    /**
     * This method queries the tweets of a particular user's handle.
     *
     * @param handle the Twitter handle (username) without the @sign
     */
    @SuppressWarnings("unchecked")
    public void queryHandle(String handle) throws IOException {
        statuses.clear();
        statuses.addAll(fetchTweets(handle));

        words.clear();
        words.addAll(splitIntoWords(toMessage(statuses)));

        this.words.removeAll(loadCommonWordsFromLocation());
        this.words.addAll(sortAndRemoveEmpties(words));
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
        Optional<URL> commonWordsURL = getCommonWordsURL(COMMON_WORDS_LOCATION);
        if (!commonWordsURL.isPresent()) {
            throw new IllegalArgumentException("Could not find common words " + COMMON_WORDS_LOCATION);
        }

        return loadCommonWordsFromStream(commonWordsURL.get().openStream());
    }

    /**
     * This method sorts the words in words in alphabetically (and lexicographic) order.
     * You should use your sorting code you wrote earlier this year.
     * Remove all empty strings while you are at it.
     *
     * @param terms
     */
    @SuppressWarnings("unchecked")
    public List<String> sortAndRemoveEmpties(List<String> terms) {
        Collections.sort(removeEmptyStrings(terms));
        return terms;
    }

    Optional<URL> getCommonWordsURL(String name) {
        return Optional.ofNullable(getClass().getResource(name));
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

    List<String> removeEmptyStrings(List<String> terms) {
        return terms.stream()
                .map(String::trim)
                .filter(s -> s.length() != 0)
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
     * This method determines how many people in Arlington, VA
     * tweet about the Miami Dolphins.  Hint:  not many. :(
     */
    public void sampleInvestigate() {
        Query query = new Query("Miami Dolphins");
        query.setCount(100);
        query.setGeoCode(new GeoLocation(38.8372839, -77.1082443), 5, Query.MILES);
        query.setSince("2015-12-1");
        try {
            QueryResult result = twitter.search(query);
            System.out.println("Count : " + result.getTweets().size());
            for (Status tweet : result.getTweets()) {
                System.out.println("@" + tweet.getUser().getName() + ": " + tweet.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        System.out.println();
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
