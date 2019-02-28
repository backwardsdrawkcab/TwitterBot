package edu.woodson;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectionUtilTest {

    @Test
    void toSingle() {
        assertEquals("test", CollectionUtil.toSingle(Collections.singleton("test")));
        assertThrows(IllegalArgumentException.class, () -> CollectionUtil.toSingle(Collections.emptySet()));
        assertThrows(IllegalArgumentException.class, () -> CollectionUtil.toSingle(Arrays.asList("test0", "test1")));
    }
}