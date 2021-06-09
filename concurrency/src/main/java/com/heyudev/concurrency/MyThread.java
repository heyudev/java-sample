package com.heyudev.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @author supeng
 * @date 2021/06/08
 */
public class MyThread {
    public static void main(String[] args) {
        ThreadOne threadOne = new ThreadOne();
        threadOne.start();
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadOne.interrupt();
        Thread.interrupted();
        threadOne.isInterrupted();

        System.out.println(threadOne.isInterrupted() + "-" + threadOne.isAlive());
        System.out.println(Thread.currentThread().isInterrupted() + "-" + Thread.currentThread().isAlive());
        for (int i = 0; i < 10; i++) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("i = " + i);
        }

        for (int i = 0; i < 10; i++) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("ii = " + i);
        }
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
                System.out.println("--------"+i++);
            }
        }
    }
}
