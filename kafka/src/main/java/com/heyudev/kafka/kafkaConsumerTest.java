package com.heyudev.kafka;

/**
 * @author heyudev
 * @date 2021/06/16
 */
public class kafkaConsumerTest {

    public static void main(String[] args) {
        OrderConsumer orderConsumer = new OrderConsumer();
        orderConsumer.consume();
    }
}
