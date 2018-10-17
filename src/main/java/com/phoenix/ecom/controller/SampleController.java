package com.phoenix.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleController {

    @GetMapping("display")
    public ResponseEntity<String> display(){
        return ResponseEntity.ok("hello");
    }

}
