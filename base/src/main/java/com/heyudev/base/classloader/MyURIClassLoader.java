package com.heyudev.base.classloader;

/**
 * @author heyudev
 * @date 2021/06/04
 */
public class MyURIClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return findResource("").getClass();
    }
}
