package com.phoenix.ecom.controller;

import com.phoenix.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleController {

    @Autowired
    ProductService productService;

    @GetMapping("display")
    public ResponseEntity<String> display(){
        String prodName = productService.listProduct("abcd");
        return ResponseEntity.ok(prodName);
    }

}
