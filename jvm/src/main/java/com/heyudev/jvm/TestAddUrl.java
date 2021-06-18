package com.heyudev.jvm;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class TestAddUrl {
    public static void main(String[] args) {
        try {
            URLClassLoader classLoader = (URLClassLoader) TestAddUrl.class.getClassLoader();
            String dir = "/Users/Heyu/Downloads/xlass";
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(classLoader, new File(dir).toURL());

            Class klass = Class.forName("Hello", true, classLoader);
            Object obj = klass.newInstance();
            Method hello = klass.getDeclaredMethod("hello");
            hello.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
