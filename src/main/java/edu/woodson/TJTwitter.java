package edu.woodson;

import twitter4j.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class TJTwitter {
    private final Twitter twitter;
    private final List<Status> statuses;
    private int numberOfTweets;
    private final List<String> terms;
    private String popularWord;
    private int frequencyMax;

    public TJTwitter(PrintStream console) {
        // Makes an instance of Twitter - this is re-usable and thread safe.
        // Connects to Twitter and performs authorizations.
        twitter = TwitterFactory.getSingleton();
        statuses = new ArrayList<>();
        terms = new ArrayList<>();
    }

    public List<String> getTerms() {
        return terms;
    }

    public int getNumberOfTweets() {
        return numberOfTweets;
    }

    public String getMostPopularWord() {
        return popularWord;
    }

    public int getFrequencyMax() {
        return frequencyMax;
    }

    /**
     * This method tweets a given message.
     * @param message message you wish to Tweet out
     * @throws TwitterException
     * @throws IOException
     */
    public void tweetOut(String message) {

    }


    /**
     * This method queries the tweets of a particular user's handle.
     *
     * @param handle the Twitter handle (username) without the @sign
     */
    @SuppressWarnings("unchecked")
    public void queryHandle(String handle) throws TwitterException, IOException {
        statuses.clear();
        terms.clear();
        fetchTweets(handle);
        splitIntoWords();
        removeCommonEnglishWords();
        sortAndRemoveEmpties();
        mostPopularWord();
    }

    /**
     * This method fetches the most recent 2,000 tweets of a particular user's handle and
     * stores them in an arrayList of Status objects.  Populates statuses.
     *
     * @param handle the Twitter handle (username) without the @sign
     */
    public void fetchTweets(String handle) throws TwitterException, IOException {
        // Creates file for debugging purposes
        PrintStream out = new PrintStream(new FileOutputStream("tweets.txt"));
        Paging page = new Paging(1, 200);
        int p = 1;
        while (p <= 10) {
            page.setPage(p);
            statuses.addAll(twitter.getUserTimeline(handle, page));
            p++;
        }
        numberOfTweets = statuses.size();
        out.println("Number of tweets = " + numberOfTweets);
    }

    /**
     * This method takes each status and splits them into individual words.
     * Store the word in terms.
     */
    public void splitIntoWords() {


    }

    /**
     * This method removes common English words from the list of terms.
     * Remove all words found in commonWords.txt  from the argument list.
     * The count will not be given in commonWords.txt. You must count the number of words in this method.
     * This method should NOT throw an exception.  Use try/catch.
     */
    @SuppressWarnings("unchecked")
    public void removeCommonEnglishWords() {


    }

    /**
     * This method sorts the words in terms in alphabetically (and lexicographic) order.
     * You should use your sorting code you wrote earlier this year.
     * Remove all empty strings while you are at it.
     */
    @SuppressWarnings("unchecked")
    public void sortAndRemoveEmpties() {


    }

    /**
     * This method calculates the word that appears the most times
     * Consider case - should it be case sensitive?  The choice is yours.
     *
     * @post will populate the frequencyMax variable with the frequency of the most common word
     */
    @SuppressWarnings("unchecked")
    public void mostPopularWord() {


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

        return "";

    }

    /******************  Part IV *******************/
    public void investigate() {
        //Enter your code here
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
}