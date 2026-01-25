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

## Implementation Details

The producer logic is implemented in `OrderService.java`.

- **Topic**: `orders`
- **Message Key**: `orderId` (Ensures ordering for updates to the same order)
- **Payload**: `OrderPlacedEvent` (JSON serialized)
- **Method**: Asynchronous sending using `KafkaTemplate` with `CompletableFuture` for success/failure callbacks.

### Event Structure (`OrderPlacedEvent`)

The event payload contains:
- `orderId` (String)
- `customerId` (String)
- `productId` (String)
- `quantity` (Integer)
- `totalAmount` (BigDecimal)
- `orderDate` (LocalDateTime)
