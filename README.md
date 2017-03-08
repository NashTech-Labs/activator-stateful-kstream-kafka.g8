#Kafka Stateful Stream Processor

This is an activator project showcasing stateful stream processing using Kafka KStream.

**Kafka Client** for Kafka API

**Twitter4J Streaming** as a source.

**Typesafe Config** to read configuration file.

**Kafka Streaming** to process stream

---
###Steps to Install and Run Zookeeper and Kafka on your system :

Step 1: Download Kafka

Download Kafka from [here](https://www.apache.org/dyn/closer.cgi?path=/kafka/0.10.1.1/kafka_2.11-0.10.1.1.tgz)

Step 2: Extract downloaded file

```bash
tar -xzvf kafka_2.11-0.10.1.1.tgz
cd kafka_2.11-0.10.1.1
```        
    
Step 3: Start Servers

Start Zookeeper:

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```
    
Start Kafka server:

```bash
bin/kafka-server-start.sh config/server.properties
```


---
###Clone Project

```bash
git clone git@github.com:knoldus/activator-stateful-kstream-kafka.git
cd activator-kstream-kafka
bin/activator clean compile
```
---
###Start Tweet Producer

```bash
bin/activator "run-main com.knoldus.demo.ProducerDemo"
```

    
This will start fetching tweets and push every tweet into the Kafka queue.

---
###Start Stream Processor

```bash
bin/activator "run-main com.knoldus.demo.KStreamDemo"
```
    
This will start stream processing.

###Start Consumer

```bash
bin/activator "run-main com.knoldus.demo.ConsumerDemo"
```

This will start hashtag count.

---
For any issue please raise a ticket @ [Github Issue](https://github.com/knoldus/activator-stateful-kstream-kafka/issues)