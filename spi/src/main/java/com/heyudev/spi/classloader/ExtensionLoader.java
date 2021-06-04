package com.heyudev.spi.classloader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author heyudev
 * @date 2021/06/04
 */
public class ExtensionLoader<T> {
    private static final String EXTENDSION_DIR = "MATE-INF/";

    private static Class<?> type;

    private ExtensionLoader(Class<?> type){
        type = type;
    }

    private static Map<Class<?>, ExtensionLoader<?>> extensionLoaderMap = new ConcurrentHashMap<Class<?>, ExtensionLoader<?>>();


}
