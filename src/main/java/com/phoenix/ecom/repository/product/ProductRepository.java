package com.phoenix.ecom.repository.product;

import com.phoenix.ecom.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository  {

    @Autowired
    MongoTemplate mongoTemplate;

    public void saveProduct(Product product) {
        mongoTemplate.insert(product, "product");
    }

}
