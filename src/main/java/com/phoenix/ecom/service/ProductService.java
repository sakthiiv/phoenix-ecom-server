package com.phoenix.ecom.service;

import com.phoenix.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public String listProduct(String nameProd){
        String name = productRepository.findByName(nameProd);
        return name;
    }
}
