package com.heyudev.concurrency;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author supeng
 * @date 2021/06/21
 */
public class MySemaphore {
    private static final ExecutorService pool = new ThreadPoolExecutor(100, 100, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().build());

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //信号量
        Semaphore semaphore = new Semaphore(4);

        IntStream.range(0, 10).forEach(i -> {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("3--" + i + "---" + semaphore.availablePermits());
                        TimeUnit.SECONDS.sleep(1);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        System.out.println("3----stop-----" + stopwatch.stop());
    }
}
