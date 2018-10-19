package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/product" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Product productRequest){
        productService.createProduct(productRequest);
        return new ResponseEntity("{ \"message\":\"Product created successfully\"}", HttpStatus.CREATED);
    }

}
