package com.heyudev.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author supeng
 * @date 2021/07/02
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new Subject();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(subject.getClass());
        enhancer.setCallback(new MyMethodInterceptor());

        Subject subject1 = (Subject) enhancer.create();
        subject1.say();
        subject1.run();
    }
}
