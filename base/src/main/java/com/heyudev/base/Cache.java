package com.heyudev.base;

import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Cache {

    private static final Map<String, Map<Object,Object>> MAP_MAP = new HashMap<>(){{
        put("1", new HashMap<>(){{put("1","11");}});
        put("2", new HashMap<>(){{put("2","22");}});
        put("3", new HashMap<>(){{put("3","33");}});
    }};

    private static LoadingCache<String, Map<Object, Object>> apExpInfoLocalCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .build(new CacheLoader<String, Map<Object, Object>>() {
                @Override
                public Map<Object, Object> load(String key) throws Exception {
                    return MAP_MAP.get(key);
                }
            });


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println(apExpInfoLocalCache.get("1"));
//        TimeUnit.SECONDS.sleep(9);
//        System.out.println(apExpInfoLocalCache.get("1"));
//        System.out.println(apExpInfoLocalCache.get("2"));
//        TimeUnit.SECONDS.sleep(11);
//        System.out.println(apExpInfoLocalCache.get("1"));
//        System.out.println(apExpInfoLocalCache.get("2"));
//        System.out.println(apExpInfoLocalCache.get("3"));

        Stopwatch stopWatch = Stopwatch.createStarted();
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) throws Exception {
                        return queryData(key);
                    }
                });
        Thread thread1 = startLoadingCacheQuery("client1", cache);
        Thread thread2 = startLoadingCacheQuery("client2", cache);
        thread1.join();
        thread2.join();
        Thread thread3 = startLoadingCacheQuery("client3", cache);
        Thread thread4 = startLoadingCacheQuery("client4", cache);
        thread3.join();
        thread4.join();
        Thread.sleep(10000);
        Thread thread5 = startLoadingCacheQuery("client5", cache);
        Thread thread6 = startLoadingCacheQuery("client6", cache);
        thread5.join();
        thread6.join();
    }
//    public void test() throws InterruptedException {
//        Stopwatch stopWatch = Stopwatch.createStarted();
//        stopWatch.start();
//        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
//                .refreshAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<Integer, String>() {
//                    @Override
//                    public String load(Integer key) throws Exception {
//                        return queryData(key);
//                    }
//                });
//        Thread thread1 = startLoadingCacheQuery("client1", cache);
//        Thread thread2 = startLoadingCacheQuery("client2", cache);
//        thread1.join();
//        thread2.join();
//        Thread thread3 = startLoadingCacheQuery("client3", cache);
//        Thread thread4 = startLoadingCacheQuery("client4", cache);
//        thread3.join();
//        thread4.join();
//        Thread.sleep(10000);
//        Thread thread5 = startLoadingCacheQuery("client5", cache);
//        Thread thread6 = startLoadingCacheQuery("client6", cache);
//        thread5.join();
//        thread6.join();
//    }

    private static String queryData(Integer key) throws InterruptedException {
        log("queryData start");
        Thread.sleep(3000);
        log("queryData end");
        return key.toString();
    }

    private static Thread startLoadingCacheQuery(String clientName, LoadingCache<Integer, String> cache) {
        Thread thread = new Thread(() -> {
            log("get start");
            try {
                cache.get(1);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            log("get end");
        });
        thread.setName(clientName);
        thread.start();
        return thread;
    }

    private static void log(String msg) {
        System.out.println(String.format("%ds %s %s", System.currentTimeMillis() / 1000, Thread.currentThread().getName(), msg));
    }
}
