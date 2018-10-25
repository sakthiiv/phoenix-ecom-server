package com.phoenix.ecom.service.checkout;

import com.phoenix.ecom.model.Cart;
import com.phoenix.ecom.model.CheckoutOrder;
import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.repository.cart.CartRepository;
import com.phoenix.ecom.repository.checkout.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CheckoutService {

    private CartRepository cartRepository;
    private CheckoutRepository checkoutRepository;

    @Autowired
    public CheckoutService(CartRepository cartRepository, CheckoutRepository checkoutRepository) {
        this.cartRepository = cartRepository;
        this.checkoutRepository = checkoutRepository;
    }

    public String checkout(String userId, String address) {

        Cart  checkoutCart = cartRepository.getCart(userId);

        List<Product> checkoutProducts = checkoutCart.getProducts();

        cartRepository.deleteCart(userId);

        CheckoutOrder newOrder = new CheckoutOrder(userId, address, checkoutProducts);

        return checkoutRepository.checkout(newOrder);
    }
}
