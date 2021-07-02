package com.heyudev.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author supeng
 * @date 2021/07/02
 */
public class DynamicProxy {
    /**
     * 被代理对象
     */
    private Object object;

    private InvocationHandler invocationHandler;

    public DynamicProxy(Object object) {
        Class<?> c = object.getClass();
        this.invocationHandler = new MyInvocationHandler(object);
        this.object = Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), invocationHandler);
    }

    public Object execute(String methodName, Object... args) {
        Class[] paramsClass = new Class[args.length];
        for (int i = 0; i < paramsClass.length; i++) {
            paramsClass[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = object.getClass().getMethod(methodName, paramsClass);
            return method.invoke(object, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
