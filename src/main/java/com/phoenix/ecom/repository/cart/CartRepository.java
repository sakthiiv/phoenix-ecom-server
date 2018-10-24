package com.phoenix.ecom.repository.cart;

import com.phoenix.ecom.model.Cart;
import com.phoenix.ecom.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository implements ICartRepository{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void addProduct(Cart cartRequest) {
        String userId = cartRequest.getUserId();
        Cart cartEntry = mongoTemplate.findOne(new Query(Criteria.where("userId").is(userId)), Cart.class);

        if(cartEntry != null){
            mongoTemplate.remove(new Query(Criteria.where("userId").is(userId)),"cart");

            List<Product> productList = new ArrayList();
            for (Product product : cartEntry.getProducts()){
                productList.add(product);
            }
            for (Product product : cartRequest.getProducts()){
                productList.add(product);
            }
            cartEntry.setProducts(productList);
            mongoTemplate.save(cartEntry, "cart");
        }else{
            mongoTemplate.save(cartRequest, "cart");
        }
    }

    @Override
    public Cart getCart(String userId) {
        return mongoTemplate.findOne(new Query(Criteria.where("userId").is(userId)), Cart.class);
    }
}
