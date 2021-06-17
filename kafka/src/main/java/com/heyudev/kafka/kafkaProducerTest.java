package com.heyudev.kafka;

import java.util.ArrayList;

/**
 * @author heyudev
 * @date 2021/06/16
 */
public class kafkaProducerTest {
    public static void main(String[] args) {
        OrderProducer orderProducer = new OrderProducer();
        for (int i = 0; i < 1000; i++) {
            Order order = new Order((long) i, new ArrayList<>(), 0.5 * i);
            orderProducer.send(order);
        }

    }
}
