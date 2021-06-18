package com.heyudev.unsafe.cas;

import com.heyudev.unsafe.wapper.UnsafeWapper;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author supeng
 * @date 2021/06/18
 */
public class MyAtomicInt {
    private static final Unsafe unsafe = UnsafeWapper.unsafeInstance();

    private static long valueOffset;

    private volatile int value;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(MyAtomicInt.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public MyAtomicInt(int value) {
        this.value = value;
    }

    public final int getValue() {
        return value;
    }

    public final void setValue(int value) {
        this.value = value;
    }

    public int incrAndGet() {
        while (unsafe.compareAndSwapInt(this, valueOffset, value, value + 1)) {
            return value + 1;
        }
        return value;
    }
}