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
docker exec -it kafka bash
```

**Change Dir for Kafka CLI scripts within the container:**
```bash
cd /opt/kafka/bin
```

**Create a new Kafka Topic:**
```bash
./kafka-topics.sh --create \
--bootstrap-server localhost:9092 \
--replication-factor 1 \
--partitions 3 \
--topic my-first-topic
```

**List all topics:**
```bash
./kafka-topics.sh --list --bootstrap-server localhost:9092
```

**Describe your topic:**
```bash
./kafka-topics.sh --describe \
--bootstrap-server localhost:9092 \
--topic my-first-topic
```

**Exit the container interactive shell:**
```bash
exit
```