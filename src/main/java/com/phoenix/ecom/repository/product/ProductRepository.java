package com.phoenix.ecom.repository.product;

import com.phoenix.ecom.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
