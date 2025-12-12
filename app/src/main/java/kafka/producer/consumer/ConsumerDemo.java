package kafka.producer.consumer;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemo {
    private static final Logger log = LoggerFactory.getLogger(ConsumerDemo.class);

    public static void main(String[] args) {
        log.info("Starting kafka consumer");

        // create properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "my-fourth-application");
        props.put("auto.offset.reset", "earliest");

        // create consumer with try-with-resources for graceful shutdown
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            log.info("Kafka Consumer created successfully");

            // Add shutdown hook for graceful closure
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                log.info("Shutting down consumer gracefully...");
                consumer.close();
            }));

            // subscribe consumer to topic
            consumer.subscribe(java.util.Collections.singleton("test-topic"));
            // poll for new data
            while (true) {
                var records = consumer.poll(java.time.Duration.ofMillis(100));
                for (var record : records) {
                    log.info("Key: " + record.key() + ", Value: " + record.value());
                    log.info("Partition: " + record.partition() + ", Offset:" + record.offset());
                }
            }
        } catch (Exception e) {
            log.error("Error in consumer", e);
        }
    }
}
