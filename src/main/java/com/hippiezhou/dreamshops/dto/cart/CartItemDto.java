package com.hippiezhou.dreamshops.dto.cart;

import com.hippiezhou.dreamshops.dto.product.ProductDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private Long itemId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private ProductDto product;

}
