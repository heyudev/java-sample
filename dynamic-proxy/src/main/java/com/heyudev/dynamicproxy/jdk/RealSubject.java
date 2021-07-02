package com.heyudev.dynamicproxy.jdk;

/**
 * 实际执行人
 *
 * @author heyudev
 * @date 2021/07/02
 */
public class RealSubject implements Subject {
    public void say() {
        System.out.println("jdk say");
    }

    public void run() {
        System.out.println("jdk running");
    }
}
