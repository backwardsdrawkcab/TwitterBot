package edu.woodson;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtil {
    private CollectionUtil() {
    }

    public static <T> T toSingle(Collection<T> collection) {
        if (collection.size() == 1) {
            throw new IllegalArgumentException("Collection does not contain a single element");
        } else {
            return new ArrayList<>(collection).get(0);
        }
    }
}
