package com.phoenix.ecom.repository.cart;

import com.phoenix.ecom.model.Cart;

public interface ICartRepository {
    void createCart(Cart cartRequest);
}
