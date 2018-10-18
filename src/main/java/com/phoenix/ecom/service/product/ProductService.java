package com.phoenix.ecom.service.product;

import com.phoenix.ecom.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService{

    @Autowired
    ProductRepository productRepository;

    public String displayProductId(String name) {
        String prodId = productRepository.findProductIdByName(name);
        return prodId;
    }
}