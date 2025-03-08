package com.hippiezhou.dreamshops.controller;

import com.hippiezhou.dreamshops.exception.ResourceNotFoundException;
import com.hippiezhou.dreamshops.response.ApiResponse;
import com.hippiezhou.dreamshops.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{cartId}/my-cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Cart fetched successfully", cartService.getCartById(cartId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to fetch cart", e.getMessage()));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to clear cart", e.getMessage()));
        }
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalAmount(@PathVariable Long cartId) {
        try {
            return ResponseEntity.ok(new ApiResponse("Total amount fetched successfully", cartService.getTotalPrice(cartId)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to fetch total amount", e.getMessage()));
        }
    }
}
