package com.heyudev.unsafe;

import com.heyudev.unsafe.cas.MyAtomicInt;
import com.heyudev.unsafe.wapper.UnsafeWapper;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author heyudev
 * @date 2021/06/18
 */
public class Test {
    public static void main(String[] args) {
        long l = 1L;
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println("address = " + unsafe.getAddress(l));

//        Unsafe unsafe1 = UnsafeWapper.unsafeInstance();
//        System.out.println("address = " + unsafe1.getAddress(l));

        MyAtomicInt myAtomicInt = new MyAtomicInt(5);
        System.out.println(myAtomicInt.incrAndGet());
        System.out.println(myAtomicInt.incrAndGet());
        System.out.println(myAtomicInt.incrAndGet());
    }
}
