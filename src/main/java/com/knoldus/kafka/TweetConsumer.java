package com.knoldus.kafka;

import com.knoldus.utils.ConfigReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class TweetConsumer {

    private ConfigReader configReader = new ConfigReader();

    public void consumeTweets(String groupId, String kafkaTopic) {
        String kafkaServers = configReader.getKafkaServers();
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServers);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
        KafkaConsumer<String, Long> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe((Collections.singletonList(kafkaTopic)));
        while (true) {
            ConsumerRecords<String, Long> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, Long> record : records) {
                System.out.println("Received: " + record.key() + " ---> " + record.value());
            }
            System.out.println("\n\n=======================\n\n");
        }
    }

}
