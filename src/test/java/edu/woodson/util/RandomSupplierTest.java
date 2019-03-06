package edu.woodson.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomSupplierTest {
    private RandomSupplier<Integer> supplier;

    @BeforeEach
    void beforeEach() {
        supplier = new RandomSupplier<>() {
            @Override
            public Integer get() {
                return 1;
            }
        };
    }

    @RepeatedTest(10)
    void create() {
        List<Integer> integers = supplier.create(0, 10);
        int size = integers.size();
        assertTrue(size > 0);
        assertTrue(size < 10);
    }

    @RepeatedTest(10)
    void createRandomNumber() {
        int randomNumber = supplier.createRandomNumber(0, 10);
        assertTrue(randomNumber >= 0);
        assertTrue(randomNumber < 10);
    }
}