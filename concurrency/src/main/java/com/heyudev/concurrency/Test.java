package com.heyudev.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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


//        List<String> list = new ArrayList<>();
//        for (int i=0;i<1000;i++) {
//            list.add(i+"_");
//        }
//
//        List<String> list1 = new ArrayList<>();
//        for (int i=0;i<1000;i++) {
//            list1.add(i+"_");
//        }
//
//        System.out.println("start");
//
//        list.parallelStream().filter(x->{
//            try {
//                TimeUnit.MILLISECONDS.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return true;
//        }).collect(Collectors.toSet());
//
//        System.out.println("running");
//
//        list1.parallelStream().filter(x->{
//            try {
//                TimeUnit.MILLISECONDS.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return true;
//        }).collect(Collectors.toSet());
//
//        System.out.println("end");


    }
}
