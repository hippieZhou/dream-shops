package com.hippiezhou.dreamshops.service;

import com.hippiezhou.dreamshops.dto.ProductDto;
import com.hippiezhou.dreamshops.model.Product;
import com.hippiezhou.dreamshops.request.ProductAddRequest;
import com.hippiezhou.dreamshops.request.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductAddRequest product);

    Product getProductById(Long id);

    void deleteProductById(Long id);

    Product updateProduct(ProductUpdateRequest request, Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategoryAndBrand(String category, String brand);

    List<Product> getProductByName(String name);

    List<Product> getProductByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);
    ProductDto convertToDTO(Product product);
}
