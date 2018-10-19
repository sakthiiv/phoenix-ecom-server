package com.phoenix.ecom.repository.product;

import com.phoenix.ecom.model.Image;
import com.phoenix.ecom.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository  {

    @Autowired
    MongoTemplate mongoTemplate;

    public void saveProduct(Product product) {
        mongoTemplate.insert(product, "product");

        Product productSaved = mongoTemplate.findOne(new Query(Criteria.where("name").is(product.getName())), Product.class);

        Image image = new Image(productSaved.getId(), product.getImageContent());
        mongoTemplate.insert(image, "image");
    }

}
