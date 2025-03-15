package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId);

    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
