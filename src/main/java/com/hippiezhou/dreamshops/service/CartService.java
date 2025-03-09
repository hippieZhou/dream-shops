package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.model.Cart;
import com.hippiezhou.dreamshops.model.User;

import java.math.BigDecimal;

public interface CartService {
    Cart getCartById(Long id);

    void clearCart(Long id);

    BigDecimal getTotalPrice(Long cartId);

    Cart initializedNewCart(User user);

    Cart getCartByUserId(Long userId);
}
