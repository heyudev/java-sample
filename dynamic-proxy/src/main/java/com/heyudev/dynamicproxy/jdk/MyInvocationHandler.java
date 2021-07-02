package com.heyudev.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author heyudev
 * @date 2021/07/02
 */
public class MyInvocationHandler implements InvocationHandler {
    /**
     * 被代理对象
     */
    private Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        before();
        result = method.invoke(object, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("before");
    }

    private void after() {
        System.out.println("after");
    }
}
