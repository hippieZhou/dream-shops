package com.hippiezhou.dreamshops.service.impl;

import com.hippiezhou.dreamshops.exception.ResourceNotFoundException;
import com.hippiezhou.dreamshops.model.Cart;
import com.hippiezhou.dreamshops.repository.CartItemRepository;
import com.hippiezhou.dreamshops.repository.CartRepository;
import com.hippiezhou.dreamshops.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    @Override
    public Cart getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = getCartById(id);
        cartItemRepository.deleteAllByCartId(cart.getId());
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long cartId) {
        Cart cart = getCartById(cartId);
        return cart.getTotalAmount();
    }

    public Long initializedNewCart() {
        Long cartId = cartIdGenerator.incrementAndGet();
        Cart newCart = new Cart();
        newCart.setId(cartId);
        return cartRepository.save(newCart).getId();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
