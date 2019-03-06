package edu.woodson;

import edu.woodson.util.CollectionUtil;
import twitter4j.Status;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TJTwitterStatistics {
    final List<Status> statuses = new ArrayList<>();
    final List<String> words = new ArrayList<>();

    public int getMaxFrequency() {
        return Math.toIntExact(calculateMax(createFrequencyMap(words)).orElseThrow());
    }

    Optional<Long> calculateMax(Map<String, Long> map) {
        return map.values().stream().max(Long::compareTo);
    }

    /******************  Part III - Test *******************/

    Map<String, Long> createFrequencyMap(List<String> words) {
        return words.stream().collect(Collectors.toMap(Function.identity(), string -> words.stream()
                .filter(s -> s.equals(string))
                .count(), Long::max));
    }

    /******************  Part III - Tweet *******************/

    public String getMostPopularWord() {
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

    long calculateMax(List<String> words, Map<String, Long> map) {
        Optional<Long> maxOptional = calculateMax(map);
        if (!maxOptional.isPresent()) {
            throw new IllegalArgumentException("No maximum found for " + words);
        }

        return maxOptional.get();
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public List<String> getWords() {
        return words;
    }

    public void removeCommonWords(List<String> commonWords) {
        words.removeAll(commonWords);
    }

    public void setValues(List<Status> statuses, List<String> words) {
        this.statuses.clear();
        this.statuses.addAll(statuses);

        this.words.clear();
        this.words.addAll(words);
    }

    /**
     * This method sorts the words in words in alphabetically (and lexicographic) order.
     * You should use your sorting code you wrote earlier this year.
     * Remove all empty strings while you are at it.
     */
    public void sortAndRemoveEntries() {
        Collections.sort(removeEmptyStrings(words));
    }

    List<String> removeEmptyStrings(List<String> terms) {
        return terms.stream()
                .map(String::trim)
                .filter(s -> s.length() != 0)
                .collect(Collectors.toList());
    }
}