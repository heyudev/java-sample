package com.heyudev.concurrency;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author supeng
 * @date 2021/06/08
 */
public class MyThread {
    public static void main(String[] args) {
//        ThreadOne threadOne = new ThreadOne();
//        threadOne.start();
//        try {
//            TimeUnit.MILLISECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        threadOne.interrupt();
//        Thread.interrupted();
//        threadOne.isInterrupted();
//
//        System.out.println(threadOne.isInterrupted() + "-" + threadOne.isAlive());
//        System.out.println(Thread.currentThread().isInterrupted() + "-" + Thread.currentThread().isAlive());
//        for (int i = 0; i < 10; i++) {
////            try {
////                TimeUnit.MILLISECONDS.sleep(10);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            System.out.println("i = " + i);
//        }
//
//        for (int i = 0; i < 10; i++) {
////            try {
////                TimeUnit.MILLISECONDS.sleep(10);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            System.out.println("ii = " + i);
//        }

//        Thread2 t1 = new Thread2();
//        t1.start();
//
//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("end");


//        Semaphore semaphore = new Semaphore(2);
//        System.out.println(semaphore.availablePermits());
//        semaphore.release();
//        System.out.println(semaphore.availablePermits());
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(semaphore.availablePermits());
//        try {
//            semaphore.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(semaphore.availablePermits());

//        Thread2 t1 = new Thread2();
//        t1.start();
//        System.out.println("A");
//        try {
//            t1.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("B");
//        t1.notify();
//        System.out.println("C");
    }


    static class ThreadOne extends Thread {
        @Override
        public void run() {
//            for (int j = 0; j < 10; j++) {
////                try {
////                    TimeUnit.MILLISECONDS.sleep(10);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                System.out.println("j = " + j);
//            }
//
//            for (int j = 0; j < 10; j++) {
////                try {
////                    TimeUnit.MILLISECONDS.sleep(10);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
//                System.out.println("jj = " + j);
//            }

            int i = 0;
            while (!this.isInterrupted()) {
                System.out.println("--------" + i++);
            }
        }
    }


    static class Thread2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("thread2 = " + i);

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
