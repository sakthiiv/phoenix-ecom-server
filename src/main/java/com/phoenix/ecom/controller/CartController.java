package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Cart;
import com.phoenix.ecom.service.cart.CartService;
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

        return new ResponseEntity<>(
                "{ \"message\":\"Product added to cart successfully\"}",
                HttpStatus.CREATED);
    }


    @GetMapping(value = "/cart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Cart> get(@PathVariable("id") String id)
    {
        Cart cart = cartService.getCart(id);

        if(cart == null || cart.getProducts().isEmpty())
        {
            return new ResponseEntity<>(new Cart(), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cart, HttpStatus.ACCEPTED.OK);
    }

    @DeleteMapping(value = "/cart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<String> delete(@PathVariable("id") String id, @RequestBody List<String> productIds) {
        cartService.deleteCart(id, productIds);
        return new ResponseEntity<>("{ \"message\":\"Product deleted from cart successfully\"}", HttpStatus.ACCEPTED.OK);
    }
}
