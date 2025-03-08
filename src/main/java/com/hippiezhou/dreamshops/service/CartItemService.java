package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.model.CartItem;

public interface CartItemService {
    void addItemToCart(Long cartId, Long productId, int quantity);

    void removeItemFromCart(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}
