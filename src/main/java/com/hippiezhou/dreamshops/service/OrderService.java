package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.model.Order;

public interface OrderService {
    Order placeOrder(Long userId);

    Order getOrder(Long orderId);
}
