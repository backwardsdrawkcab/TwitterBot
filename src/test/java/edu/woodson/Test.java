package edu.woodson;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Test {
    public static void main(String[] args) {
        Configuration builder = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey("egk7giRtajHZYXBbKAjzSVhWb")
                .setOAuthConsumerSecret("bLiAVPF6q14TIztSlsatAKRGUenRKQC5MKtWEfAB8xTfqweeHr")
                .setOAuthAccessToken("1059514626868740096-T5G4cTrK6NExdx1j33ss69X9ODnMq4")
                .setOAuthAccessTokenSecret("pHoPJfLiWHtNbEUmx0vOnFdMnn6O4Ajvpvs3IcJkwDeAY")
                .build();

        Twitter twitter = new TwitterFactory(builder).getInstance();
        try {
            Status status = twitter.updateStatus("Hello World!");
            System.out.println(status);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
