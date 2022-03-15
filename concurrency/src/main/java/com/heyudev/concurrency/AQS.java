package com.heyudev.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author heyudev
 * @date 2021/06/07
 */
public class AQS {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        //
        lock.lock();
        try {

        } catch (Exception e) {

        }finally {
            lock.unlock();
        }
    }
}
