package com.heyudev.base.classloader;

/**
 * 1. 对class加密
 * 2. 记载自定义路径的class  插件机制
 *
 * @author heyudev
 * @date 2021/06/04
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
