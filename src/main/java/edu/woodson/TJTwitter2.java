package edu.woodson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

class TJTwitter2 {
    private final List<Status> statuses;
    private int numberOfTweets;
    private List<String> words;
    private int frequencyMax;

    public TJTwitter2() {
        statuses = new ArrayList<>();
        words = new ArrayList<>();
    }

    public List<String> getWords() {
        return words;
    }

    public int getFrequencyMax() {
        return frequencyMax;
    }

    @SuppressWarnings("unchecked")
    public void queryHandle(String handle) throws IOException {
        statuses.clear();
        words.clear();
        fetchTweets(handle);
        words = splitIntoWords(statuses);
        removeCommonEnglishWords();
        words = sortAndRemoveEmpties(words);
        Optional<Integer> popularWord = highestFrequency(words);
        if (popularWord.isPresent()) {
            System.out.println("Max Frequency: " + popularWord.get());
            frequencyMax = popularWord.get();
        }
    }

    /**
     * This method reads a file of tweets and
     * stores them in an arrayList of Status objects.
     * Populates statuses.
     *
     * @param handle the text file
     */
    public void fetchTweets(String handle) throws IOException {
        Scanner scan = new Scanner(new File(handle));
        while (scan.hasNext())
            statuses.add(new Status(scan.nextLine()));
        numberOfTweets = statuses.size();
    }

    /**
     * This method takes each status and splits them into individual words.
     * Store each word in words.
     *
     * @param statuses The statuses to parse into words.
     */
    public List<String> splitIntoWords(List<Status> statuses) {
        return statuses.stream()
                .map(Status::getText)
                .map(StringTokenizer::new)
                .map(Enumeration::asIterator)
                .map(objectIterator -> (Iterable<Object>) () -> objectIterator)
                .map(Iterable::spliterator)
                .flatMap(spliterator -> StreamSupport.stream(spliterator, false))
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    /**
     * This method removes common English words from the list of words.
     * Remove all words found in commonWords.txt  from the argument list.
     * The count will not be given in commonWords.txt. You must count the number of words in this method.
     * This method should NOT throw an exception.  Use try/catch.
     */
    @SuppressWarnings("unchecked")
    public void removeCommonEnglishWords() throws IOException {
        //your code goes here
        URL url = getClass().getResource("commonWords.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        Set<String> commonWords = reader.lines().collect(Collectors.toSet());
        reader.close();

        words.removeAll(commonWords);
    }

    /**
     * <p>
     * * This method sorts the words in words in alphabetically (and lexicographic) order.
     * * You should use your sorting code you wrote earlier this year.
     * * Remove all empty strings while you are at it.
     * </p>
     *
     * @param terms The words.
     */
    public List<String> sortAndRemoveEmpties(List<String> terms) {
        terms.sort(String::compareTo);
        terms.removeIf(s -> s.trim().isEmpty());
        return terms;
    }

    /**
     * This method calculates the word that appears the most times
     * Consider case - should it be case sensitive?  The choice is yours.
     *
     * @param terms The words.
     * @post will populate the frequencyMax variable with the frequency of the most common word
     */
    @SuppressWarnings("unchecked")
    public Optional<Integer> highestFrequency(List<String> terms) {
        return generateWordMap(terms)
                .values()
                .stream()
                .max(Integer::compareTo);
    }

    private Map<String, Integer> generateWordMap(List<String> terms) {
        Map<String, Integer> wordMap = new HashMap<>();
        terms.stream()
                .map(String::toLowerCase)
                .forEach(word -> {
                    int value = wordMap.containsKey(word)
                            ? wordMap.get(word) + 1
                            : 0;
                    wordMap.put(word, value);
                });
        return wordMap;
    }

    public int getNumberOfTweets() {
        return numberOfTweets;
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
        return s.replaceAll("[.!?\\-]", "");
    }

    public String getMostPopularWord() {
        return CollectionUtil.toSingle(getMostPopularWords());
    }

    private Set<String> getMostPopularWords() {
        Optional<Integer> optional = highestFrequency(words);
        if (optional.isPresent()) {
            int highestFrequency = optional.get();
            Map<String, Integer> wordMap = generateWordMap(words);
            return wordMap.keySet()
                    .stream()
                    .filter(s -> wordMap.get(s).equals(highestFrequency))
                    .collect(Collectors.toSet());
        } else {
            throw new IllegalArgumentException("No valid words found");
        }
    }
}
