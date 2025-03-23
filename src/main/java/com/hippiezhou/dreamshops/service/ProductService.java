package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.dto.product.ProductDto;
import com.hippiezhou.dreamshops.controller.product.request.ProductAddRequest;
import com.hippiezhou.dreamshops.controller.product.request.ProductUpdateRequest;
import com.hippiezhou.dreamshops.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductAddRequest product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(ProductUpdateRequest request, Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductByName(String name);

    List<Product> getProductByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
