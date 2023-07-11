package com.ismailcet.productservice.service;

import com.ismailcet.productservice.dto.ProductRequest;
import com.ismailcet.productservice.dto.ProductResponse;
import com.ismailcet.productservice.entity.Product;
import com.ismailcet.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        log.info("Product {} is saved",product.getId());
        return productRepository.save(product);

    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product e) {
        return ProductResponse.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .price(e.getPrice())
                .build();
    }
}
