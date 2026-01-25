package com.subhanmishra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private static final String TOPIC_NAME = "orders";

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public OrderService(KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderPlacedEvent(OrderRequest request) {

        // Create the event using record constructor
        var event = new OrderPlacedEvent(
                UUID.randomUUID().toString(),  // orderId
                request.customerId(),
                request.productId(),
                request.quantity(),
                request.totalAmount(),
                LocalDateTime.now()
        );

        log.info("Publishing OrderPlacedEvent: {}", event);

        // Send message asynchronously
        CompletableFuture<SendResult<String, OrderPlacedEvent>> future =
                kafkaTemplate.send(TOPIC_NAME, event.orderId(), event);

        // Handle success
        future.thenAccept(result -> {
            var metadata = result.getRecordMetadata();
            log.info("Message sent successfully! Topic: {}, Partition: {}, Offset: {}",
                    metadata.topic(),
                    metadata.partition(),
                    metadata.offset());
        });

        // Handle failure
        future.exceptionally(ex -> {
            log.error("Failed to send message: {}", ex.getMessage(), ex);
            return null;
        });
    }
}
