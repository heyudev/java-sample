package com.heyudev.concurrency;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author heyudev
 * @date 2021/06/04
 */
public class Test {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            public void run() {
//                System.out.println("t1 start");
//                StaticWait staticWait = new StaticWait();
//                staticWait.say2(Thread.currentThread().getName());
//                staticWait.sayHello("t1");
//                System.out.println("t1 end");
//            }
//        }, "t1").start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                System.out.println("t2 start");
//                StaticWait staticWait = new StaticWait();
//                staticWait.sayHello("t2");
//                StaticWait.say1("t2");
//                System.out.println("t2 end");
//            }
//        }, "t2").start();

//        new Thread(new Runnable() {
//            public void run() {
//                System.out.println("t3 start");
//                StaticWait.say1(Thread.currentThread().getName());
//                StaticWait staticWait = new StaticWait();
//                staticWait.sayHello("t3");
//                System.out.println("t3 end");
//            }
//        }, "t3").start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(new Runnable() {
//            public void run() {
//                System.out.println("t4 start");
//                StaticWait staticWait = new StaticWait();
//                staticWait.sayHello("t4");
//                System.out.println("t4 end");
//            }
//        }, "t4").start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                System.out.println("t5 start");
//                StaticWait.say3(Thread.currentThread().getName());
//                System.out.println("t5 end");
//            }
//        }, "t5").start();
    }
}
