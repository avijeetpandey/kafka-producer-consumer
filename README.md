## Kafka Examples using JAVA

### Kafka commands
- Listing the topics
    - > kafka-topics --bootstrap-server localhost:9092 --list
- Creating topic with partition and replication factor
    - > kafka-topics --bootstrap-server localhost:9092 --topic demoTopicOne --create --partition 5 --replication-factor 1