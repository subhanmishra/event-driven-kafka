# Kafka CLI

**Start Kafka:**
```bash
docker-compose up -d
```

**Verify Kafka container is up:**
```bash
docker ps
```

**Launch Interactive shell for running container:**
```bash
docker exec -it kafka-1 bash
```

**Change Dir for Kafka CLI scripts within the container:**
```bash
cd /opt/kafka/bin
```

**Create a new Kafka Topic:**
```bash
./kafka-topics.sh --create \
  --bootstrap-server kafka-1:19092 \
  --topic replicated-topic \
  --partitions 3 \
  --replication-factor 2
```

**Describe your topic:**
```bash
./kafka-topics.sh --describe \
  --bootstrap-server kafka-1:19092 \
  --topic replicated-topic
```

**Kafka Producer**
```bash
./kafka-console-producer.sh \
  --bootstrap-server kafka-1:19092 \
  --topic replicated-topic
```

**Kafka1 consumer**
```bash
./kafka-console-consumer.sh \
  --bootstrap-server kafka-1:19092 \
  --topic replicated-topic \
  --from-beginning
```

**Kafka2 consumer**
```bash
./kafka-console-consumer.sh \
  --bootstrap-server kafka-2:19092 \
  --topic replicated-topic \
  --from-beginning
```

**Kafka3 consumer**
```bash
./kafka-console-consumer.sh \
  --bootstrap-server kafka-3:19092 \
  --topic replicated-topic \
  --from-beginning
```

**Exit the container interactive shell:**
```bash
exit
```
