package com.heyudev.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author heyudev
 */
public abstract class Counter {
//    private final AtomicInteger counter = new AtomicInteger(0);
    private final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();

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

    public void increment(String key) {
        AtomicInteger counter = counters.computeIfAbsent(key, k -> new AtomicInteger(0));
        int current = counter.incrementAndGet();
        if (current >= threshold) {
            resetAndDeal(key);
        }
    }

    private void resetAndDeal(String key) {
        AtomicInteger counter = counters.get(key);
        if (counter == null) {
            return;
        }

        int current;
        do {
            current = counter.get();
            if (current < threshold) {
                // 其他线程已处理
                return;
            }
        } while (!counter.compareAndSet(current, current - threshold));
        // 处理阈值数量的数据
        deal(key, threshold);
    }

    /**
     * 上报
     *
     * @param count 数量
     */
    protected abstract void deal(String key, int count);

    /**
     * 定时上报剩余数据
     */
    public void flush(String key) {
        AtomicInteger counter = counters.get(key);
        if (counter == null) {
            return;
        }
        int current = counter.get();
        if (current > 0 && counter.compareAndSet(current, 0)) {
            deal(key, current);
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
