package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto placeOrder(Long userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
