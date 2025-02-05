package com.heyudev.base;

public class TestMain {
    private static final String REDIS_KEY_FORMAT = "video:history:status:%s:%d";
    @lombok.SneakyThrows
    public static void main(String[] args) {
        System.out.println(Math.floorMod(10,3));
        System.out.println(Math.floorMod(-10,3));
        System.out.println(Math.floorMod(10,-3));
        System.out.println(Math.floorMod(-10,-3));
        System.out.println("-----------------------");
        System.out.println(10%3);
        System.out.println(-10%3);
        System.out.println(10%(-3));
        System.out.println(-10%-3);
    }
}
