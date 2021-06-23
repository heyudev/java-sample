package com.heyudev.base;

/**
 * @author supeng
 * @date 2021/06/23
 */
public class BitOperation {
    public static void main(String[] args) {
        int a =6;
        int b =14;
        String s1 = Integer.toBinaryString(a);
        String s2 = Integer.toBinaryString(b);
        System.out.println(s1);
        System.out.println(s2);
        int i1 = Integer.parseInt(String.valueOf(s1.charAt(0)));
        int i2 = Integer.parseInt(String.valueOf(s2.charAt(0)));
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i1 & i2);
        System.out.println(i1 | i2);
        System.out.println(~i1);
        System.out.println(i1 ^ i2);
        for (int i=0;i<10;i++) {
            System.out.println(i ^ 0);
        }

    }
}
