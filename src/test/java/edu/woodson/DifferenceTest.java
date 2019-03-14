package edu.woodson;

import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

public class DifferenceTest {

    public static void main(String[] args) {
        List<String> testBig = new LinkedList<>();
        testBig.add("a");
        testBig.add("c");
        testBig.add("b");

        List<String> testSmall = new LinkedList<>();
        testSmall.add("a");
        testSmall.add("b");

        System.out.println();
        List<String> result = new LinkedList<>(CollectionUtils.subtract(testBig, testSmall));
        for (String c : result) {
            System.out.println(c);
        }
    }
}
