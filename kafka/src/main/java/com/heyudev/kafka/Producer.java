package com.heyudev.kafka;

/**
 * @author heyudev
 * @date 2021/06/17
 */
public interface Producer<T> {
    void send(T t);
    void close();
}
