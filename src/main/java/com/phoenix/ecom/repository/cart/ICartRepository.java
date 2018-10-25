package com.phoenix.ecom.repository.cart;

import com.phoenix.ecom.model.Cart;

import java.util.List;

public interface ICartRepository {

    void addProduct(Cart cart);

    Cart getCart(String userId);

    void deleteCart(String userId, List<String> productIds);

    void deleteCart(String userId);
}
