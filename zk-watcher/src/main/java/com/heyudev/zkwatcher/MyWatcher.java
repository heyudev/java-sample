package com.heyudev.zkwatcher;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author heyudev
 * @date 2021/06/09
 */
public class MyWatcher {
    public static void main(String[] args) {

        String address = "localhost:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(address, new ExponentialBackoffRetry(1000, 3));
        try {
            client.start();
            String path = "/test";
//            final PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
//            System.out.println(pathChildrenCache.getCurrentData());

            addNodeCacheListener(client,path);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (client != null) {
//                client.close();
//            }
        }

    }

    /**
     * PathChild
     * Node
     * Tree
     */
    private static void addNodeCacheListener(CuratorFramework curatorFramework,String path) throws Exception {
        final NodeCache nodeCache=new NodeCache(curatorFramework,path,false);
        NodeCacheListener nodeCacheListener=new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("Receive Node Changed");
                System.out.println(""+nodeCache.getCurrentData().getPath()+"->"+new String(nodeCache.getCurrentData().getData()));
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();
    }
    private static void addPathChildCacheListener(CuratorFramework curatorFramework,String path) throws Exception {
        PathChildrenCache childrenCache=new PathChildrenCache(curatorFramework,path,true);
        PathChildrenCacheListener childrenCacheListener=new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("子节点事件变更的回调");
                ChildData childData=pathChildrenCacheEvent.getData();
                System.out.println(childData.getPath()+"-"+new String(childData.getData()));
            }
        };
        childrenCache.getListenable().addListener(childrenCacheListener);
        childrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }
}
