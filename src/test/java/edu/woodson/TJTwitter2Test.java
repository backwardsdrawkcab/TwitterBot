package edu.woodson;

import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TJTwitter2Test {

    @org.junit.jupiter.api.Test
    public void removePunctuationShouldReturnStringWithoutPunctuation() {
        try {
            TJTwitter2 twitter = new TJTwitter2();
            assertEquals("boop", twitter.removePunctuation("?Boop!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}