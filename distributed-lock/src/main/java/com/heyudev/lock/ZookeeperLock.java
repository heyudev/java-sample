package com.heyudev.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

/**
 * Zookeeper 实现分布式锁
 *
 * @author supeng
 * @date 2021/04/08
 */
public class ZookeeperLock {

    /**
     * ZooKeeper 锁节点路径, 分布式锁的相关操作都是在这个节点上进行
     */
    private final String lockPath = "/distributed-lock";

    /**
     * ZooKeeper 服务地址, 单机格式为:(127.0.0.1:2181),
     * 集群格式为:(127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183)
     */
    private String connectString;

    /**
     * Curator 客户端重试策略
     */
    private RetryPolicy retry;

    /**
     * Curator 客户端对象
     */
    private CuratorFramework client;

    /**
     * 共享锁，不可重入
     */
    final InterProcessLock lock = new InterProcessSemaphoreMutex(client, lockPath);

    /**
     * 共享锁  可重入
     */
    final InterProcessLock lock2 = new InterProcessMutex(client, lockPath);

    /**
     * 共享锁  可重入 读写锁
     */
    final InterProcessReadWriteLock lock3 = new InterProcessReadWriteLock(client, lockPath);

    /**
     * 加锁
     *
     * @return
     */
    public static boolean lock() {
        return false;
    }

    /**
     * 解锁
     *
     * @return
     */
    public static boolean unlock() {
        return false;
    }
}
