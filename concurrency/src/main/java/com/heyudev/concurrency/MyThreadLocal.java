package com.heyudev.concurrency;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author supeng
 * @date 2021/06/21
 */
public class MyThreadLocal {

    private static final ExecutorService pool = new ThreadPoolExecutor(100, 100, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().build());

    static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("1");

        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set("2");

        System.out.println(threadLocal.get() + "--1--" + inheritableThreadLocal.get());

        pool.execute(() -> System.out.println(threadLocal.get() + "--2--" + inheritableThreadLocal.get()));

        new Thread(() -> System.out.println(threadLocal.get() + "--3--" + inheritableThreadLocal.get())).start();

        //value 强引用
        User user = new User("value");
        ThreadLocal<User> threadLocal1 = new ThreadLocal<>();
        threadLocal1.set(user);

        // 回收
        threadLocal1.remove();
    }
}
