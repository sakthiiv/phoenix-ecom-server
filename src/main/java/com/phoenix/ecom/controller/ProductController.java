package com.phoenix.ecom.controller;

import com.phoenix.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
