package com.heyudev.dynamicproxy.jdk;

/**
 * @author heyudev
 * @date 2021/06/18
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        DynamicProxy proxy = new DynamicProxy(subject);
        proxy.execute("say");
        proxy.execute("run");
    }
}
