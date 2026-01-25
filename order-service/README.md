# Order Service

This service handles order processing and publishes events to Kafka.

## Kafka Producer Configuration

The Kafka producer is configured in `src/main/resources/application.yml`.

### Key Settings:

- **Bootstrap Servers**: `localhost:9092`
- **Key Serializer**: `StringSerializer`
- **Value Serializer**: `JsonSerializer`

### Reliability & Performance:

- **Acks**: `all` (Wait for all replicas for maximum reliability)
- **Retries**: `3` (Retry up to 3 times on failure)
- **Idempotence**: `true` (Ensures exactly-once delivery semantics)
- **Batch Size**: `16384` (16KB)
- **Linger ms**: `10` (Wait 10ms to batch messages)
- **Delivery Timeout**: `120000` ms
