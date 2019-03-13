package edu.woodson.lab;

import edu.woodson.util.CollectionUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TJTwitterStatistics {
    final List<String> words = new ArrayList<>();

    public TJTwitterStatistics(String... words) {
        this.words.addAll(Arrays.asList(words));
    }

    public int getMaxFrequency() {
        return Math.toIntExact(calculateMax(createFrequencyMap()).orElseThrow());
    }

    Optional<Long> calculateMax(Map<String, Long> map) {
        return map.values().stream().max(Long::compareTo);
    }

    /******************  Part III - Test *******************/

    Map<String, Long> createFrequencyMap() {
        return words.stream().collect(Collectors.toMap(Function.identity(), string -> words.stream()
                .filter(s -> s.equals(string))
                .count(), Long::max));
    }

    /******************  Part III - Tweet *******************/

    public String getMostPopularWord() {
        return CollectionUtil.toSingle(mostPopularWord());
    }

    /**
     * This method calculates the word that appears the most times
     * Consider case - should it be case sensitive?  The choice is yours.
     * <p>
     * post will populate the maxFrequency variable with the frequency of the most common word
     *
     * @post will populate the maxFrequency variable with the frequency of the most common word
     */
    @SuppressWarnings("unchecked")
    public Set<String> mostPopularWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException("No words found for " + words);
        }

        Map<String, Long> map = createFrequencyMap();
        Optional<Long> max = calculateMax(map);
        if(!max.isPresent()){
            throw new IllegalStateException("No max found");
        }

        return map.keySet()
                .stream()
                .filter(s -> map.get(s).equals(max))
                .collect(Collectors.toSet());
    }

    public void removeCommonWords(List<String> commonWords) {
        words.removeAll(commonWords);
    }

    public void setValues(List<String> words) {
        this.words.clear();
        this.words.addAll(words);
    }

    /**
     * This method sorts the words in words in alphabetically (and lexicographic) order.
     * You should use your sorting code you wrote earlier this year.
     * Remove all empty strings while you are at it.
     */
    public List<String> sortAndRemoveEntries() {
        Collections.sort(removeEmptyStrings());
        return words;
    }

    List<String> removeEmptyStrings() {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            String s = word.trim();
            if (s.length() != 0) {
                list.add(s);
            }
        }
        return list;
    }
}