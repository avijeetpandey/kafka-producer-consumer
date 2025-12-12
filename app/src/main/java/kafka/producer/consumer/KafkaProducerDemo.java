package kafka.producer.consumer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class KafkaProducerDemo {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerDemo.class);

    public static void main(String[] args) {
        log.info("Kafka Producer Demo started");
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        // creating the kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        log.info("Kafka Producer created successfully");

        // create producer record
        ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", "key1", "value1");

        producer.send(record);

        producer.flush();
        producer.close();
        log.info("Kafka Producer Demo completed");
    }
}