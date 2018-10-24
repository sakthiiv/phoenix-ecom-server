package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Cart;
import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.service.cart.CartService;
import com.phoenix.ecom.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CartController {

    @Autowired
    CartService cartService;
    @PostMapping(value = "/cart" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Cart cartRequest){
        cartService.createCart(cartRequest);
        return new ResponseEntity<>("{ \"message\":\"Product added to cart successfully\"}", HttpStatus.CREATED);
    }

}
