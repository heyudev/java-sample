package com.heyudev.concurrency;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author heyudev
 * @date 2021/06/09
 */
public class MyFuture {


    static class User {
        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    private static final ExecutorService pool = new ThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().build());

    public static void main(String[] args) {

        Future<User> future = pool.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    System.out.println("1---" + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return new User("lili", 15);
            }
        });

        try {
            System.out.println("future done = " + future.isDone());
            System.out.println("future = " + future.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        MyTask myTask = new MyTask();
        FutureTask<User> futureTask = new FutureTask<User>(myTask);
        pool.submit(futureTask);

        try {
            System.out.println("futureTask done= " + futureTask.isDone());
            System.out.println("futureTask = " + futureTask.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Supplier<User> supplier = () -> new User("jerry", 145);
        CompletableFuture<User> completableFuture = CompletableFuture.supplyAsync(supplier, pool);

        completableFuture.thenAccept(result -> {
            System.out.println("user : " + result.toString());
        });

        completableFuture.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });

        System.out.println("completableFuture done = " + completableFuture.isDone());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }

    static class MyTask implements Callable<MyFuture.User> {

        public User call() throws Exception {
            return new User("tom", 15);
        }
    }
}
