package edu.woodson;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtil {
    private CollectionUtil() {
    }

    public static <T> T toSingle(Collection<T> collection) {
        if (collection.size() == 1) {
            return new ArrayList<>(collection).get(0);
        } else {
            throw new IllegalArgumentException("Collection does not contain a single element");
        }
    }
}
