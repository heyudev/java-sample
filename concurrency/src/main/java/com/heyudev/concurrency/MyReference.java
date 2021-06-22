package com.heyudev.concurrency;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author supeng
 * @date 2021/06/21
 */
public class MyReference {

    static class User {
        public User() {
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //强引用
        //没有使用才会被回收
        TimeUnit.SECONDS.sleep(60);
        User user = new User();
        user = null;
        System.gc();


        TimeUnit.SECONDS.sleep(60);
        //软引用 如果一个对象只具有软引用，在内存足够时，垃圾回收器不会回收它；如果内存不足，就会回收这个对象的内存
        //使用场景：缓存   例如：图片缓存框架
        //10M
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        long maxMemory = Runtime.getRuntime().maxMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("maxMemory = " + maxMemory);
        System.out.println("freeMemory = " + freeMemory);
//        userSoftReference.clear();
        System.gc();
        byte[] bytes = new byte[(int) (freeMemory * 2)];

        System.out.println(softReference.get());

        TimeUnit.SECONDS.sleep(60);
        //弱引用  将对象留在内存的能力不是那么强的引用。当垃圾回收器扫描到只具有弱引用的对象，不管当前内存空间是否足够，都会回收内存
        //使用场景：ThreadLocal ：
        WeakReference<User> weakReference = new WeakReference<>(new User());
        System.out.println(weakReference.get());
        System.gc();

        TimeUnit.SECONDS.sleep(60);
        //虚引用
        ReferenceQueue<User> queue = new ReferenceQueue<>();
        PhantomReference<User> phantomReference = new PhantomReference<>(new User(), queue);


        System.in.read();
    }
}
