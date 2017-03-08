package com.knoldus.kafka;

import com.knoldus.utils.ConfigReader;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashTagCount {

    private ConfigReader configReader = new ConfigReader();

    public void hashTagCounter() {
        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "find-top-hashtag");
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        streamsConfiguration.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        String topic = configReader.getKStreamTopic();
        String producerTopic = configReader.getKafkaTopic();

        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();

        KStreamBuilder builder = new KStreamBuilder();
        KStream<String, String> textLines = builder.stream(stringSerde, stringSerde, producerTopic);

        KStream<String, Long> hashTags = textLines
                .flatMapValues(HashTagCount::getAllHashtag)
                .groupBy((key, value) -> value)
                .count("Counts")
                .toStream();

        hashTags.to(stringSerde, longSerde, topic);

        KafkaStreams streams = new KafkaStreams(builder, streamsConfiguration);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    private static List<String> getAllHashtag(String tweet) {
        Pattern regExPattern = Pattern.compile("#(\\w+)");
        List<String> hashtags = new ArrayList<>();
        Matcher mat = regExPattern.matcher(tweet);
        while (mat.find()) {
            hashtags.add(mat.group());
        }
        return hashtags;
    }

}
