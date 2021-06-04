package com.heyudev.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author heyudev
 * @date 2021/04/09
 */
public class WaitAndNotify {
    
    public static void main(String[] args) {
        final Beer beer = new Beer();
        new Thread(new Runnable() {
            public void run() {
                beer.get();
            }
        }, "get-thread").start();

        new Thread(new Runnable() {
            public void run() {
                beer.lose();
            }
        }, "lose-thread").start();

        System.out.println("-----------------------------------------------------------------------------------------------");

        final BlackBeer blackBeer = new BlackBeer();

        new Thread(new Runnable() {
            public void run() {
                blackBeer.get();
            }
        }, "get-thread").start();

        new Thread(new Runnable() {
            public void run() {
                blackBeer.lose();
            }
        }, "lose-thread").start();

    }
}

class Beer {
    int count = 0;
    Object o = new Object();
    public void get() {
        for (int i = 0; i < 100; i++) {
            synchronized (o){
                if (count>20) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    count++;
                }
                System.out.println("get count = " + count);
                o.notifyAll();
            }
        }
    }

    public  void lose()  {
        for (int i = 0; i < 100; i++) {
            synchronized(o){
                if (count<=0) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    count--;
                }
                System.out.println("lose count = " + count);
                o.notifyAll();
            }
        }
    }
}

class BlackBeer {
    int count = 0;
    public synchronized void get() {
        for (int i = 0; i < 100; i++) {
            if (count>20) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                count++;
            }
            System.out.println("get count = " + count);
            notifyAll();
        }
    }

    public synchronized void lose()  {
        for (int i = 0; i < 100; i++) {
            if (count<=0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                count--;
            }
            System.out.println("lose count = " + count);
            notifyAll();
        }
    }
}


class WhiteBeer {
    int count = 0;
    Lock lock = new ReentrantLock();
    public void get() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            if (count>20) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                count++;
            }
            System.out.println("get count = " + count);
            notifyAll();
        }
    }

    public void lose()  {
        for (int i = 0; i < 100; i++) {
            if (count<=0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                count--;
            }
            System.out.println("lose count = " + count);
            notifyAll();
        }
    }
}