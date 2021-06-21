package com.heyudev.concurrency;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author supeng
 * @date 2021/06/21
 */
public class MyCyclicBarrier {
    private static final ExecutorService pool = new ThreadPoolExecutor(100, 100, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().build());

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //回环栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("2--end--" + Thread.currentThread().getName());
            }
        });
        IntStream.range(0, 10).forEach(i -> {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("2--" + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        System.out.println("2----stop-----"+stopwatch.toString());

        try {
            System.out.println("sleep 10s start");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("sleep 10s end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
