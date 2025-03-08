package com.hippiezhou.dreamshops.service.impl;

import com.hippiezhou.dreamshops.exception.ResourceNotFoundException;
import com.hippiezhou.dreamshops.model.Cart;
import com.hippiezhou.dreamshops.model.CartItem;
import com.hippiezhou.dreamshops.model.Product;
import com.hippiezhou.dreamshops.repository.CartItemRepository;
import com.hippiezhou.dreamshops.repository.CartRepository;
import com.hippiezhou.dreamshops.service.CartItemService;
import com.hippiezhou.dreamshops.service.CartService;
import com.hippiezhou.dreamshops.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartService cartService;

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        //1. get the cart
        //2. get the product
        //3. check if the product is already in the cart
        //4. if yes, increase the quantity with the requested quantity
        //5. if no, the initial a new CartItem entity with the requested quantity
        Cart cart = cartService.getCartById(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst().orElse(new CartItem());
        if (cartItem.getId() == null) {
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
            cart.getItems().add(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCartById(cartId);
        CartItem cartItem = getCartItem(cartId, productId);
        cart.removeItem(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCartById(cartId);
        cart.getItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .ifPresent(item -> {
                item.setQuantity(quantity);
                item.setUnitPrice(item.getProduct().getPrice());
                item.setTotalPrice();
            });
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCartById(cartId);
        return cart.getItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst().orElseThrow(() -> new ResourceNotFoundException(productId));
    }
}
