package com.hippiezhou.dreamshops.service.impl;

import com.hippiezhou.dreamshops.dto.ImageDto;
import com.hippiezhou.dreamshops.dto.ProductDto;
import com.hippiezhou.dreamshops.exception.ResourceAlreadyExistsException;
import com.hippiezhou.dreamshops.exception.ResourceNotFoundException;
import com.hippiezhou.dreamshops.model.Category;
import com.hippiezhou.dreamshops.model.Image;
import com.hippiezhou.dreamshops.model.Product;
import com.hippiezhou.dreamshops.repository.CategoryRepository;
import com.hippiezhou.dreamshops.repository.ImageRepository;
import com.hippiezhou.dreamshops.repository.ProductRepository;
import com.hippiezhou.dreamshops.request.ProductAddRequest;
import com.hippiezhou.dreamshops.request.ProductUpdateRequest;
import com.hippiezhou.dreamshops.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Override
    public Product addProduct(ProductAddRequest request) {
        // check if the category is found in the database
        // if yes, set it as the new request's category
        // if no, create a new category and set it as the new request's category
        // save the new request to the database
        // return the new request
        if (productExists(request.getBrand(), request.getName())) {
            throw new ResourceAlreadyExistsException("Product already exists :" + request.getBrand() + " " + request.getName() + ", you can update it instead");
        }
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
            .orElseGet(() -> categoryRepository.save(new Category(request.getCategory().getName())));
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private boolean productExists(String name, String brand) {
        return productRepository.existsByNameAndBrand(name, brand);
    }

    private Product createProduct(ProductAddRequest request, Category category) {
        return new Product(
            request.getName(),
            request.getBrand(),
            request.getPrice(),
            request.getInventory(),
            request.getDescription(),
            category
        );
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, () ->
        {
            throw new ResourceNotFoundException(id);
        });
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Product product) {
        return productRepository.findById(product.getId())
            .map(existingProduct -> updateExistingProduct(existingProduct, request))
            .map(productRepository::save)
            .orElseThrow(() -> new ResourceNotFoundException(product.getId()));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream().map(image -> new ImageDto(image.getId(), image.getFileName(), image.getDownloadUrl())).toList();
        return new ProductDto(product.getId(), product.getName(), product.getBrand(), product.getPrice(), product.getInventory(), product.getDescription(),
            product.getCategory(), imageDtos);
    }
}
