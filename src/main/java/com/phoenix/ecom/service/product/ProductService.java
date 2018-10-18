package com.phoenix.ecom.service.product;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public String createProduct(Product product) {
        return productRepository.saveProduct(product);
    }
}