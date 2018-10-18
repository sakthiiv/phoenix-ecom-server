package com.phoenix.ecom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.ecom.dto.product.ProductDto;
import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/product")
    public ResponseEntity<String> create(@RequestBody ProductDto productRequest){

        Product product = new Product(productRequest.getName(), productRequest.getPrice(), productRequest.getCategoryId(), productRequest.getSubCategoryId(), productRequest.getDescription(), productRequest.isValid());
        String id = productService.createProduct(product);
        return ResponseEntity.ok(id);
    }

}
