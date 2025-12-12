## Kafka Examples using JAVA

### Kafka commands
- Listing the topics
    - > kafka-topics --bootstrap-server localhost:9092 --list
- Creating topic with partition and replication factor
    - > kafka-topics --bootstrap-server localhost:9092 --topic demoTopicOne --create --partition 5 --replication-factor 1

## Notes on Apache Kafka
Before definition some key points
OPS - operation per second
Throughput - Number of operations per second, in case of database it is very less, we cant perform high OPS taks


### Apache Kafka the what and why 
Apache kafka is an open source event streaming platform , that has high throughput and can process a lot of records at a time , as fundamentally DB's have less throughput, so frequent I/O in the DB's is expensive

Kafka is not an alternative to database, although it goes hand in hand, it has high throughput , but its storage is very low and it cant hold large amount of data, in DB you can query easily but in Kafka you cant query.

Use cases of kafka
- Messaging system
- Activity tracking
- Gather metrics from different locations
- App log gathering
- Stream processing 
- Micro services pub/sub

### Architecture of Kafka
The usual architecture of kafka is
> Kafka -> topics -> partitions

Note
1 consumer can consume multiple partitions but 1 partition is only consumed by 1 consumer , to solve this we have something called consumer groups.
Self balancing happens at consumer group level and in a consumer group there can't be multiple consumers group if the same partition or else they will sit idle. Kafka represents a Queue as well as Pub/Sub model


### Kafka topics
Kafka topic is a particular stream of data like a table in the database,but without the constraints , a kafka can have as many topics as possible and a topic is identified by its name , it does not have data verification, and the sequence of data is called stream, A topic cannot be queried and topics are immuatable and once the data is written to a partition it cant be changed.


### Kafka partiton
Topics are split into parittions , messages within each partition are ordered , ead message gets an incremental ID called offset.
Data is only kept for a short time , the default time is 1 week but can be configured.
Order of data is only guarnteed only for a partition but not accross the partitions , data is assigned randomly to a partition unless a key is provided.


### Kafka Message anatomy
lets say if the message contains key it will be always stored , in the same partition, if it does not have key it will follow a round robin approach.

"murmur2" algorithm is used for message key hashing in kafka