package com.hippiezhou.dreamshops.request;

import com.hippiezhou.dreamshops.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductAddRequest {
    private String name;
    private String brand;
    private BigDecimal price;
    private String inventory;
    private String description;
    private Category category;
    private String[] images;
}
