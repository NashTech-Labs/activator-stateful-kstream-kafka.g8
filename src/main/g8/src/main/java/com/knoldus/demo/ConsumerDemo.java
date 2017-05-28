package com.knoldus.demo;

import com.knoldus.kafka.TweetConsumer;
import com.knoldus.utils.ConfigReader;

public class ConsumerDemo {



    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader();
        String kafkaTopic = configReader.getKStreamTopic();
        String kafkaGroupId = "my-group";
        System.out.println("Kafka Topics: " + kafkaTopic);
        System.out.println("Kafka Group ID: " + kafkaGroupId);
        new TweetConsumer().consumeTweets(kafkaGroupId, kafkaTopic);
    }

}
