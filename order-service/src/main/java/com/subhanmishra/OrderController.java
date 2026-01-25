package com.subhanmishra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest request) {

        // Publish to Kafka
        orderService.sendOrderPlacedEvent(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(String.format("Order placed successfully! For CustomerID:%s and ProductID:%s", request.customerId(), request.productId()));
    }
}
