package com.heyudev.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author heyudev
 * @date 2021/06/16
 */
public class kafkaProducer {

    private static final String server = "localhost:9092";
    private static final String topic = "test-topic";
    private static final String groupId = "test-consumer-group";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", server);
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        producer.send(new ProducerRecord<>(topic, "test"));
    }
}
