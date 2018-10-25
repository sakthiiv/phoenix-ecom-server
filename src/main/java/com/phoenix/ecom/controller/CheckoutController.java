package com.phoenix.ecom.controller;

import com.phoenix.ecom.service.checkout.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @PostMapping(value = "/checkout/{userId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@PathVariable("userId") String userId, @RequestBody String address){

        String orderId = checkoutService.checkout(userId, address);

        return new ResponseEntity<>(
                "{ \"message\":\"Order placed successfully with Order Id: " + orderId +"\"}",
                HttpStatus.OK);
    }
}
