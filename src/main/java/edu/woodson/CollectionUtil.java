package edu.woodson;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtil {
    private CollectionUtil() {
    }

    public static <T> T toSingle(Collection<T> ts) {
        if (ts.size() != 1) {
            throw new IllegalArgumentException("Size is not 1");
        } else {
            return new ArrayList<>(ts).get(0);
        }
    }
}
