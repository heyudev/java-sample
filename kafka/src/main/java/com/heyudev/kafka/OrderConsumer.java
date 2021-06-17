package com.heyudev.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

/**
 * @author heyudev
 * @date 2021/06/17
 */
public class OrderConsumer implements Consumer {
    private Properties properties;
    private KafkaConsumer<String, String> consumer;
    private final String topic = "test-topic";
    private final String groupId = "test-consumer-group";
    private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
    private Set<String> orderSet = new HashSet<>();
    private volatile boolean flag = true;

    public OrderConsumer() {
        properties = new Properties();
        properties.put("enable.auto.commit", false);
        properties.put("isolation.level", "read_committed");
        properties.put("auto.offset.reset", "latest");
        properties.put("group.id", groupId);
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer(properties);
    }

    @Override
    public void consume() {
        consumer.subscribe(Collections.singletonList(topic));

        try {
            while (true) { //拉取数据
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));

                for (ConsumerRecord o : poll) {
                    ConsumerRecord<String, String> record = (ConsumerRecord) o;
                    Order order = JSONObject.parseObject(record.value(), Order.class);
                    System.out.println(" order = " + order);
                    deduplicationOrder(order);
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no matadata"));
                    consumer.commitAsync(currentOffsets, new OffsetCommitCallback() {
                        @Override
                        public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                            if (exception != null) {
                                exception.printStackTrace();
                            }
                        }
                    });
                }
            }
        } catch (CommitFailedException e) {
            e.printStackTrace();
        } finally {
            try {
                consumer.commitSync();//currentOffsets);
            } catch (Exception e) {
                consumer.close();
            }
        }
    }

    @Override
    public void close() {
        if (this.flag) {
            this.flag = false;
        }
        consumer.close();
    }


    private void deduplicationOrder(Order order) {
        orderSet.add(order.getId().toString());
    }
}
