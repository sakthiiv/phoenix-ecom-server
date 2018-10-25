package com.phoenix.ecom.repository.cart;

import com.phoenix.ecom.model.Cart;
import com.phoenix.ecom.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public void deleteCart(String userId, List<String> productIds) {
        Cart cart = mongoTemplate.findOne(new Query(Criteria.where("userId").is(userId)), Cart.class);
        List<Product> productList;

        if(cart != null && !cart.getProducts().isEmpty()){
            productList = cart.getProducts();
            Arrays.stream(productList.toArray()).filter(product -> productIds.stream().anyMatch(productId -> ((Product) product).getId().equals(productId))).forEach(productList::remove);

            if(!productList.isEmpty()) {
                mongoTemplate.findAndModify(new Query(Criteria.where("userId").is(cart.getUserId())), new Update().set("products", cart.getProducts()), Cart.class);
            }else{
                mongoTemplate.remove(new Query(Criteria.where("userId").is(cart.getUserId())), "cart");
            }
        }
    }

    @Override
    public void deleteCart(String userId) {
        mongoTemplate.remove(new Query(Criteria.where("userId").is(userId)), "cart");
    }
}
