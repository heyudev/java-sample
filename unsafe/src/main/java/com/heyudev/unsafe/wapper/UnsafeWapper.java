package com.heyudev.unsafe.wapper;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * jdk8及以下 直接使用unsafe 会报异常 java.lang.SecurityException: Unsafe
 * 通过反射获取
 *
 * @author heyudev
 * @date 2021/06/18
 */
public class UnsafeWapper {

    private static Class unsafeClass;
    private static Unsafe unsafeInstance;

    private UnsafeWapper() {
    }

    public static void init() {
        if (unsafeClass == null) {
            try {
                unsafeClass = Class.forName("sun.misc.Unsafe");
                Field field = unsafeClass.getDeclaredField("theUnsafe");
                boolean isAcessible = field.isAccessible();
                if (!isAcessible) {
                    field.setAccessible(true);
                    Object o = field.get(null);
                    unsafeInstance = o instanceof Unsafe ? (Unsafe) o : null;
                    field.setAccessible(isAcessible);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static Unsafe unsafeInstance() {
        if (unsafeInstance == null) {
            init();
        }
        return unsafeInstance;
    }
}
