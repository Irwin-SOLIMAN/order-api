package com.irwin.order_api.dto;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequestDTO(
        UUID customerId,
        List<CreateOrderLineRequestDTO> lines
) {
}
