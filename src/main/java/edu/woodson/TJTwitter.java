package edu.woodson;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
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
    private final TJTwitterStatistics statistics = new TJTwitterStatistics();
    private final Twitter twitter;

    public TJTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public int getMaxFrequency() {
        return statistics.getMaxFrequency();
    }

    /******************  Part III - Tweet *******************/

    public String getMostPopularWord() {
        return statistics.getMostPopularWord();
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
        List<Status> statuses = fetchTweets(handle);
        List<String> words = splitIntoWords(toMessage(statistics.getStatuses()));
        statistics.setValues(statuses, words);

        statistics.removeCommonWords(loadCommonWordsFromLocation());
        statistics.sortAndRemoveEntries();
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
