package com.heyudev.concurrency;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author heyudev
 * @date 2021/06/21
 */
public class MyCountDownLatch {
    private static final ExecutorService pool = new ThreadPoolExecutor(100, 100, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().build());

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        IntStream.range(0, 10).forEach(i -> {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("1--" + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            });
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("1----stop-----" + stopwatch.toString());
        //多阶段栅栏

    }
}
