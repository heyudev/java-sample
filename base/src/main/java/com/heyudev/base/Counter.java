package com.heyudev.base;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author heyudev
 */
public abstract class Counter {
    private final AtomicInteger counter = new AtomicInteger(0);
    // 可配置阈值
    private final int threshold;
    private final ScheduledExecutorService scheduler;

    protected Counter(int threshold, long interval, TimeUnit unit) {
        if (threshold <= 0) {
            throw new IllegalArgumentException("阈值必须大于0");
        }
        this.threshold = threshold;
        // 单线程定时任务
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        // 定时处理
        this.scheduler.scheduleAtFixedRate(this::flush, interval, interval, unit);
    }

    public void increment() {
        int current = counter.incrementAndGet();
        if (current >= threshold) {
            resetAndDeal();
        }
    }

    private void resetAndDeal() {
        int current;
        do {
            current = counter.get();
            if (current < threshold) {
                // 其他线程已处理
                return;
            }
        } while (!counter.compareAndSet(current, current - threshold));
        // 处理阈值数量的数据
        deal(threshold);
    }

    /**
     * 上报
     *
     * @param count 数量
     */
    protected abstract void deal(int count);

    /**
     * 定时上报剩余数据
     */
    public void flush() {
        int current = counter.get();
        if (current > 0 && counter.compareAndSet(current, 0)) {
            deal(current);
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
