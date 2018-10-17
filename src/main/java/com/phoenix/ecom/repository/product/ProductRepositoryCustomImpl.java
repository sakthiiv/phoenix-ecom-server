package com.phoenix.ecom.repository.product;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.repository.product.ProductRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public String findProductIdByName(String name) {
        Product product = mongoTemplate.findOne(new Query(Criteria.where("prodName").is(name)), Product.class);
        return product.getId();
    }
}
