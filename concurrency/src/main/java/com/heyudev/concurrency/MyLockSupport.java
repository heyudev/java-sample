package com.heyudev.concurrency;

import java.util.concurrent.locks.LockSupport;

/**
 * @author heyudev
 * @date 2021/06/21
 */
public class MyLockSupport {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                LockSupport.park();
                System.out.println("t1 run");
            }
        });
        t1.start();
        LockSupport.unpark(t1);
        System.out.println("t1 unpark 1");
    }
}
