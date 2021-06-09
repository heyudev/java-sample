package com.heyudev.concurrency;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author supeng
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

    }

    private static final ExecutorService pool = new ThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder().build());

    public static void main(String[] args) {

        Future future = pool.submit(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("2---" + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        for (int i = 0; i < 10; i++) {
            System.out.println("future--" + future.isCancelled() + "--" + future.isDone());
            if (i==5){
                System.out.println("future--cancel--"+future.cancel(true));
            }
        }

//        try {
//            System.out.println("future = " + future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        Future<User> future1 = pool.submit(new Callable() {
//            public Object call() throws Exception {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println("3---" + i);
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
////                return "f1";
//                return new User("lili", 15);
//            }
//        });
//
//        try {
//            System.out.println("future1 = " + future1.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        pool.shutdown();
    }
}
