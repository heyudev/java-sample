package com.heyudev.base;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author supeng
 * @date 2021/06/07
 */
public class MyCollection {


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a","a");
        map.get("a");
        map.remove("a");
        map.size();

        Set<String> set = new HashSet<String>();
        set.add("a");
        set.contains("a");
        set.remove("a");
        set.size();

        Map<String,String> cMap = new ConcurrentHashMap<String, String>();
        cMap.put("c","c");
        cMap.get("c");
        cMap.remove("c");
        cMap.size();

        //默认按照key排序
        Map<String,String> map1 = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        List<String> list = new ArrayList<String>();
        list.add("");
        list.get(1);
        list.remove("");


        List<String> list1 = Collections.synchronizedList(new ArrayList<String>());
        list1.add("");
        list1.get(1);
        list1.remove("");

        List<String> list2 = new CopyOnWriteArrayList<String>();
        list2.add("");
        list2.get(1);
        list2.remove("");

    }
}
