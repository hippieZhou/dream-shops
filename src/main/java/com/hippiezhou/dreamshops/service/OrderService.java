package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.dto.OrderDto;
import com.hippiezhou.dreamshops.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId);

    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
