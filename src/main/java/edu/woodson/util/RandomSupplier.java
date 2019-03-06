package edu.woodson.util;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class RandomSupplier<T> implements Supplier<T> {
    public List<T> create(int from, int to) {
        return IntStream.range(0, createRandomNumber(from, to))
                .mapToObj(value -> get())
                .collect(Collectors.toList());
    }
    private int createRandomNumber(int from, int to) {
        return (int) (Math.random() * (from - to) + to);
    }
}
