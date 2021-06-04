package com.heyudev.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @author heyudev
 * @date 2021/06/04
 */
public class StaticWait {

    public synchronized static void say1(String s) {
        System.out.println("start say1:" + s);
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end say1:" + s);
    }

    public static void say3(String s) {
        System.out.println("start say1:" + s);
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end say1:" + s);
    }

    public synchronized void say2(String s) {
        System.out.println("start say2:" + s);
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end say2:" + s);
    }


    public void sayHello(String s) {
        System.out.println("hello " + s);
    }
}
