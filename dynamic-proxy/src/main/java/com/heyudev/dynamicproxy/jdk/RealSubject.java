package com.heyudev.dynamicproxy.jdk;

/**
 * 实际执行人
 *
 * @author supeng
 * @date 2021/07/02
 */
public class RealSubject implements Subject {
    public void say() {
        System.out.println("say hello");
    }

    public void run() {
        System.out.println("i am running");
    }
}
