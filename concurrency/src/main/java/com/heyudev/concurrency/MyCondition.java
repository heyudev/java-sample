package com.heyudev.concurrency;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author supeng
 * @date 2022/03/15
 */
public class MyCondition {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private Queue<String> queue = new LinkedList<>();

    public void addTask(String task){
        lock.lock();
        try {
            queue.add(task);
            condition.signalAll();
        }catch (Exception e) {
        }finally {
            lock.unlock();
        }
    }

    public String getTask(){
        lock.lock();
        try {
            while (queue.isEmpty()){
                condition.await();
            }
            return queue.poll();
        }catch (Exception e) {

        }finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        MyCondition myCondition = new MyCondition();
        myCondition.addTask("1");
        myCondition.addTask("2");
        myCondition.addTask("3");

        System.out.println(myCondition.getTask());
        System.out.println(myCondition.getTask());
        System.out.println(myCondition.getTask());
        System.out.println(myCondition.getTask());
        System.out.println(myCondition.getTask());
        myCondition.addTask("4");
        System.out.println(myCondition.getTask());
        System.out.println(myCondition.getTask());
    }
}
