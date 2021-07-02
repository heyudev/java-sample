package com.heyudev.dynamicproxy.cglib;

/**
 * 不是接口
 *
 * @author supeng
 * @date 2021/07/02
 */
public class Subject {
    public void say(){
        System.out.println("cglib say");
    }

    public void run(){
        System.out.println("cglib running");
    }
}
