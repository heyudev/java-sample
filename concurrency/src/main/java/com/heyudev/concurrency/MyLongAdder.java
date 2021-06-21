package com.heyudev.concurrency;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author supeng
 * @date 2021/06/21
 */
public class MyLongAdder {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.add(1);
        longAdder.longValue();
        AtomicLong atomicLong = new AtomicLong(1);
        atomicLong.incrementAndGet();
        atomicLong.get();
    }
}
