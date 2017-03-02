package com.knoldus.demo;

import com.knoldus.kafka.TweetConsumer;

public class ConsumerDemo {

    public static void main(String[] args) {
        String kafkaTopic = args[0];
        String kafkaGroupId = args[1];
        System.out.println("Kafka Topics: " + kafkaTopic);
        System.out.println("Kafka Group ID: " + kafkaGroupId);
        new TweetConsumer().consumeTweets(kafkaGroupId, kafkaTopic);
    }

}
