package edu.woodson;// Name:
// Date:

import java.io.IOException;

class Twitter_Warmup {
    public static void main(String[] args) throws IOException {
        TJTwitter2 twitter = new TJTwitter2();

        //  testing remove punctuation
        String s1 = "abcd?";
        String s2 = "!abc$d";
        String s3 = "ab:cd..";
        String s4 = "abc'd";
        System.out.println(s1 + " without puncutation is " + twitter.removePunctuation(s1));
        System.out.println(s2 + " without puncutation is " + twitter.removePunctuation(s2));
        System.out.println(s3 + " without puncutation is " + twitter.removePunctuation(s3));
        System.out.println(s4 + " without puncutation is " + twitter.removePunctuation(s4));

        System.out.println();

        String f1 = "test.txt";
        String f2 = "story.txt";
        System.out.println("For the file: " + f1);
        twitter.queryHandle(f1);
        System.out.println("Most popular word: " + twitter.getMostPopularWord());
        System.out.println("Frequency: " + twitter.getFrequencyMax());
        System.out.println();

        System.out.println("For the file: " + f2);
        twitter.queryHandle(f2);
        System.out.println("Most popular word: " + twitter.getMostPopularWord());
        System.out.println("Frequency: " + twitter.getFrequencyMax());
    }
}

