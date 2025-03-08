package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.model.Cart;

import java.math.BigDecimal;

public interface CartService {
    Cart getCartById(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long cartId);

    Long initializedNewCart();

    Cart getCartByUserId(Long userId);
}
