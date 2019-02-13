package edu.woodson;// Name:
// Date:

import twitter4j.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Twitter_Driver {

    public static void main(String[] args) throws TwitterException, IOException {
        PrintStream consolePrint = System.out;

        // PART III - Connect
        // set classpath, edit properties file

        TJTwitter bigBird = new TJTwitter(consolePrint);

        // Part III - Tweet
        // Create and set a String called message below
        // Uncomment this line to test, but then recomment so that the same
        // tweet does not get sent out over and over.
   
      /*
      String message="Gooooooooo Colonials!";
      bigBird.tweetOut(message);
      */


        // PART III - Test
        // Choose a public Twitter user's handle

        Scanner scan = new Scanner(System.in);
        consolePrint.print("Please enter a Twitter handle, do not include the @symbol --> ");
        String twitter_handle = scan.next();

        // Find and print the most popular word they tweet
        while (!twitter_handle.equals("done")) {
            bigBird.queryHandle(twitter_handle);
            consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.getMostPopularWord() + ".");
            consolePrint.println("The word appears " + bigBird.getFrequencyMax() + " times.");
            consolePrint.println();
            consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
            twitter_handle = scan.next();
        }

        // PART IV
        //bigBird.investigate();

    }
}

