package com.phoenix.ecom.repository.cart;

import com.phoenix.ecom.model.Cart;

public interface ICartRepository {

    void addProduct(Cart cart);

    Cart getCart(String userId);
}
