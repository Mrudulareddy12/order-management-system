package org.example.service;

import org.example.data.OrderRequestPayload;

public interface OrderService {
    String orderProduct(Long userId, OrderRequestPayload payload);
}
