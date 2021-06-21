package com.heyudev.concurrency;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author supeng
 * @date 2021/06/21
 */
public class MyReference {

    static class User {
        public User() {
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        //强引用
        //没有使用才会被回收
        User user = new User();
        user = null;
        System.gc();

        //软引用

        //弱引用

        //虚引用





        System.in.read();
    }
}
