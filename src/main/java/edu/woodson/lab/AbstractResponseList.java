package edu.woodson.lab;

import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;

import java.util.ArrayList;

/**
 * @author SirMathhman
 * @version 0.0.0
 * @since 3/13/2019
 */
public abstract class AbstractResponseList<T> extends ArrayList<T> implements ResponseList<T> {
    @Override
    public RateLimitStatus getRateLimitStatus() {
        return null;
    }

    @Override
    public int getAccessLevel() {
        return 0;
    }
}
