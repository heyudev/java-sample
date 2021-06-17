package com.heyudev.kafka;

/**
 * @author heyudev
 * @date 2021/06/17
 */
public interface Consumer {
    void consume();
    void close();
}
