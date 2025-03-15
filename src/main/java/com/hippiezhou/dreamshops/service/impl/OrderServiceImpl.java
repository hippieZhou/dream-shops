package com.hippiezhou.dreamshops.service.impl;

import com.hippiezhou.dreamshops.dto.OrderDto;
import com.hippiezhou.dreamshops.enums.OrderStatus;
import com.hippiezhou.dreamshops.exception.ResourceNotFoundException;
import com.hippiezhou.dreamshops.mapper.OrderMapper;
import com.hippiezhou.dreamshops.model.Cart;
import com.hippiezhou.dreamshops.model.Order;
import com.hippiezhou.dreamshops.model.OrderItem;
import com.hippiezhou.dreamshops.model.Product;
import com.hippiezhou.dreamshops.repository.OrderRepository;
import com.hippiezhou.dreamshops.repository.ProductRepository;
import com.hippiezhou.dreamshops.service.CartService;
import com.hippiezhou.dreamshops.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);

        Order order = createOrder(cart);
        List<OrderItem> orderItems = createOrderItems(order, cart);

        order.setOrderItems(new HashSet<>(orderItems));
        order.setTotalAmount(calculateTotalPrice(orderItems));

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());

        return orderMapper.convertToDto(savedOrder);
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(
                order,
                product,
                cartItem.getQuantity(),
                cartItem.getUnitPrice());
        }).toList();
    }

    private BigDecimal calculateTotalPrice(List<OrderItem> orderItems) {
        return orderItems
            .stream()
            .map(orderItem -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId).map(orderMapper::convertToDto)
            .orElseThrow(() -> new ResourceNotFoundException(orderId));
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId).stream().map(orderMapper::convertToDto).toList();
    }
}
