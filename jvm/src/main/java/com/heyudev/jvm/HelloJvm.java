package com.heyudev.jvm;

/**
 * @author heyudev
 * @date 2021/06/18
 */
public class HelloJvm {
    public static void main(String[] args) {

    }

    public void hello() {
        int i = 10;
        float f = 0.5f;
        byte b = 'b';
        double d = 5.4;
        boolean flag = true;
        char c = 'c';
        short s = 1;
        long l = 10921231231L;

        if (!"".equals("1")) {
            System.out.println("if i+l = " + (i + l));
        }

        for (int m = 0; m < 10; m++) {
            System.out.println("for m*b = " + m * b);
        }
    }
}
