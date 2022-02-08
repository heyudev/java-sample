package com.heyudev.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author heyudev
 * @date 2022/02/08
 */
public class MyBlockingQueue {

    public static void main(String[] args) {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(5);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    linkedBlockingQueue.put(String.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("put " + i);
            }
        }).start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String take = linkedBlockingQueue.take();
                    System.out.println("take " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("---------------------");
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        try {
            arrayBlockingQueue.add("");
            arrayBlockingQueue.offer("");
            arrayBlockingQueue.put("1");
            arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
