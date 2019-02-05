package edu.woodson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TJTwitter2 {
    private List<TJ_Status2> statuses;
    private int numberOfTweets;
    private List<String> terms;
    private String popularWord;
    private int frequencyMax;

    public TJTwitter2() throws IOException {
        statuses = new ArrayList<TJ_Status2>();
        terms = new ArrayList<String>();
    }

    private static void sort(Comparable[] array) {
        //you will want additional helper methods
    }

    public List<String> getTerms() {
        return terms;
    }

    public String getMostPopularWord() {
        return popularWord;
    }

    public int getFrequencyMax() {
        return frequencyMax;
    }

    @SuppressWarnings("unchecked")
    public void queryHandle(String handle) throws IOException {
        statuses.clear();
        terms.clear();
        fetchTweets(handle);
        System.out.println("Number of tweets: " + getNumberOfTweets());
        splitIntoWords();
        System.out.println("All the words: " + terms);
        removeCommonEnglishWords();
        System.out.println("Remove common words: " + terms);
        sortAndRemoveEmpties();
        System.out.println("Sorted: " + terms);
        mostPopularWord();
    }

    public int getNumberOfTweets() {
        return numberOfTweets;
    }

    /**
     * This method reads a file of tweets and
     * stores them in an arrayList of TJ_Status2 objects.
     * Populates statuses.
     *
     * @param String the text file
     */
    public void fetchTweets(String handle) throws IOException {
        Scanner scan = new Scanner(new File(handle));
        while (scan.hasNext())
            statuses.add(new TJ_Status2(scan.nextLine()));
        numberOfTweets = statuses.size();
    }

    /**
     * This method takes each status and splits them into individual words.
     * Store each word in terms.
     */
    public void splitIntoWords() {
        //your code goes here

    }

    /**
     * This method removes common English words from the list of terms.
     * Remove all words found in commonWords.txt  from the argument list.
     * The count will not be given in commonWords.txt. You must count the number of words in this method.
     * This method should NOT throw an excpetion.  Use try/catch.
     */
    @SuppressWarnings("unchecked")
    public void removeCommonEnglishWords() {
        //your code goes here

    }

    /**
     * This method sorts the words in terms in alphabetically (and lexicographic) order.
     * You should use your sorting code you wrote earlier this year.
     * Remove all empty strings while you are at it.
     */
    @SuppressWarnings("unchecked")
    public void sortAndRemoveEmpties() {
        //your code goes here


    }

    /**
     * This method calculates the word that appears the most times
     * Consider case - should it be case sensitive?  The choice is yours.
     *
     * @post will popopulate the frequencyMax variable with the frequency of the most common word
     */
    @SuppressWarnings("unchecked")
    public void mostPopularWord() {
        //your code goes here
    }

    /**
     * This method removes common punctuation (but not apostrophes) from each individual word.
     * This method changes everything to lower case.
     * Consider reusing code you wrote for a previous lab.
     * Consider if you want to remove the # or @ from your words. Could be interesting to keep (or remove).
     *
     * @ param String  the word you wish to remove punctuation from
     * @ return String the word without any punctuation, all lower case
     */
    public String removePunctuation(String s) {
        //your code goes here
        return "";
    }
}
