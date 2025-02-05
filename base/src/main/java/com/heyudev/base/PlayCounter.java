package com.heyudev.base;

import java.util.concurrent.TimeUnit;

/**
 * @author heyudev
 */
public class PlayCounter extends Counter{

    protected PlayCounter(int threshold, long interval, TimeUnit unit) {
        super(threshold, interval, unit);
    }

    @Override
    protected void deal(String key, int count) {

    }
}
