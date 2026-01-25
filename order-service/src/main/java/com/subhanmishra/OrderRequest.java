package com.subhanmishra;

import java.math.BigDecimal;

public record OrderRequest(
        String customerId,
        String productId,
        Integer quantity,
        BigDecimal totalAmount
) {
}
