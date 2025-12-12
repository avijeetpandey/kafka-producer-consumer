package kafka.producer.consumer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerWithCallback {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerWithCallback.class);

    public static void main(String[] args) {
        log.info("Kafka Producer with Callback Demo started");

        // creating properties for the producer
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        // creating the kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        log.info("Kafka Producer created successfully");

        for (long i = 0; i <= 1000000000; i++) {
            // creating kafka producer record
            ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", "key2", "value2" + i);

            // calling the send method with a callback
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    log.info("Message sent successfully to topic: {}, partition: {}, offset: {}",
                            metadata.topic(), metadata.partition(), metadata.offset());
                } else {
                    log.error("Error while producing message: ", exception);
                }
            });
        }

        // closing the producer
        producer.flush();
        producer.close();

        log.info("Kafka Producer with Callback Demo completed");
    }
}
