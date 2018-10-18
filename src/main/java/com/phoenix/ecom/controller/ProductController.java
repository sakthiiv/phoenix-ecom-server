package com.phoenix.ecom.controller;

import com.phoenix.ecom.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("displayId")
    public ResponseEntity<String> display(){
        String prodID = productService.displayProductId("laptop");
        return ResponseEntity.ok(prodID);
    }
}


