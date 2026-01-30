package com.irwin.order_api.dto;

import java.util.UUID;

public record CreateOrderLineRequestDTO(
        UUID productId,
        double quantity
) {
}
