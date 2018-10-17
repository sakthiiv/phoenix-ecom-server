package com.phoenix.ecom.service;

import com.phoenix.ecom.repository.product.ProductRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepositoryCustomImpl productRepositoryCustom;

    public String displayProductId(String name) {
        String prodId = productRepositoryCustom.findProductIdByName(name);
        return prodId;
    }
}