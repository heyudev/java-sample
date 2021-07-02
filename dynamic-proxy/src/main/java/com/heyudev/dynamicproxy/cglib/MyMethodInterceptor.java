package com.heyudev.dynamicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author heyudev
 * @date 2021/07/02
 */
public class MyMethodInterceptor implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        before();
        result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }


    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }

}
