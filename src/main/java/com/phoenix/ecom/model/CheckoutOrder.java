package com.phoenix.ecom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "CheckoutOrder")
public class CheckoutOrder {

    @Id
    private String orderId;
    private final String userId;
    private final String address;
    private final List<Product> checkoutProducts;

    public CheckoutOrder(String userId, String address, List<Product> checkoutProducts) {
        this.userId = userId;
        this.address = address;
        this.checkoutProducts = checkoutProducts;
    }
}
