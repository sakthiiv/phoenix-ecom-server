package com.phoenix.ecom.service.product;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.repository.product.IProductRepository;
import com.phoenix.ecom.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private IProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
        productRepository.saveProduct(product);
    }
}