package com.phoenix.ecom.repository.checkout;

import com.phoenix.ecom.model.CheckoutOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CheckoutRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public String checkout(CheckoutOrder newOrder) {

        mongoTemplate.save(newOrder, "checkoutOrder");
        return "abcd";
    }
}
