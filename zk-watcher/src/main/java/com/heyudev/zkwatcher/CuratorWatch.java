package com.heyudev.zkwatcher;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @program: curator-zk
 * @description:
 * @author: zhanghz001
 * @create: 2021-02-07 16:46
 **/
public class CuratorWatch {

    private static CuratorFramework client = null;

    public static void main(String[] args) {
        /*
         *
         * @param connectString       连接字符串。zk server 地址和端口 "192.168.149.135:2181,192.168.149.136:2181"
         * @param sessionTimeoutMs    会话超时时间 单位ms
         * @param connectionTimeoutMs 连接超时时间 单位ms
         * @param retryPolicy         重试策略
         */
       /* //重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,10);
        //1.第一种方式
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.149.135:2181",
                60 * 1000, 15 * 1000, retryPolicy);*/

        //重试策略
        //@param baseSleepTimeMs 间隔时间
        //         @param maxRetries 重试次数
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(
                3000, 3);

        //第二种方式
        client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(10 * 1000)
                .retryPolicy(retryPolicy)
                //命名空间,都是在这个节点下
                .namespace("my")
                .build();
        //开启连接
        client.start();

        try {
            watchNodeCache();
            watch02();
            watch03();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        if (client != null) {
//            client.close();
//        }
    }

    /*
    •Curator引入了 Cache 来实现对 ZooKeeper 服务端事件的监听。
    •ZooKeeper提供了三种Watcher：
    •NodeCache : 只是监听某一个特定的节点
    •PathChildrenCache : 监控一个ZNode的子节点
    TreeCache : 可以监控整个树上的所有节点，类似于PathChildrenCache和NodeCache的组合
    * */
    /**
     * 演示 NodeCache：给指定一个节点注册监听器
     */
    public static void watchNodeCache() throws Exception {
        //1. 创建NodeCache对象
        //     * @param client curztor client
        //      * @param path the full path to the node to cache
        final NodeCache nodeCache = new NodeCache(client, "/test");

        //通过三个watcher来做
        // 2. 注册监听
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("监听到了变化");
                byte[] data = nodeCache.getCurrentData().getData();
                System.out.println("w1---"+new String(data)+"---"+nodeCache.getCurrentData().getStat());
            }
        });
        //3. 开启监听.如果设置为true，则开启监听是，加载缓冲数据
        nodeCache.start(true);
        while (true) {

        }
    }

    /**
     * 演示 PathChildrenCache：监听某个节点的所有子节点们
     * 只是子节点,比如create /itheima1/create2/create44 bbb 可以监听
     * create /itheima1/create2/create22/create333/create444 aaa 就不可以,因为是好几层子节点了
     */
    public static void watch02() throws Exception {
        //1. 创建监听器
        PathChildrenCache cache = new PathChildrenCache(client, "/test2", true);

        //2. 注册监听
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client,
                                   PathChildrenCacheEvent event) throws Exception {
                System.out.println("监听到了变化");
                byte[] data = event.getData().getData();
                System.out.println("w2---"+new String(data)+"---"+event.getType()+"---"+event.getInitialData().size()+"---"+event.getData().getStat());
            }
        });
        cache.start();
        while (true) {

        }

    }

    /**
     * 演示 TreeCache：监听某个节点自己和所有子节点们
     * 监控中可以看出,这个是整个树都会监控,包括所有子节点,不仅仅是下一层的,到叶子节点都监控
     */
    public static void watch03() throws Exception {
        //创建坚监听节点
        TreeCache treeCache = new TreeCache(client, "/test3");
        //注册监听
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client,
                                   TreeCacheEvent event) throws Exception {
                System.out.println("treeCache监控到了");
                System.out.println(event);
                System.out.println(new String(event.getData().getData()));
                System.out.println("w3---"+new String(event.getData().getData())+"---"+event.getType()+"--"+event.getData().getStat());
            }
        });
        treeCache.start();
        while (true) {

        }
    }
}
