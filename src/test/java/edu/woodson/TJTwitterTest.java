package edu.woodson;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import twitter4j.Twitter;

class TJTwitterTest {
    private TJTwitter twitter;

    @BeforeEach
    void beforeEach() {
        twitter = new TJTwitter(Mockito.mock(Twitter.class));
    }


}