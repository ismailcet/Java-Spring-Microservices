package com.ismailcet.productservice.controller;

import com.ismailcet.productservice.dto.ProductRequest;
import com.ismailcet.productservice.dto.ProductResponse;
import com.ismailcet.productservice.entity.Product;
import com.ismailcet.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()//ResponseEntity<Product>
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }


}
