package com.phoenix.ecom.service.cart;

import com.phoenix.ecom.model.Cart;
import com.phoenix.ecom.repository.cart.CartRepository;
import com.phoenix.ecom.repository.cart.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private ICartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void createCart(Cart cartRequest) {
        cartRepository.addProduct(cartRequest);
    }

    public Cart getCart(String userId) {
        return cartRepository.getCart(userId);
    }

    public void deleteCart(String id, List<String> productIds) {
        cartRepository.deleteCart(id, productIds);
    }
}
