package com.phoenix.ecom.repository.product;

import com.phoenix.ecom.model.Product;

public interface IProductRepository {

    String findProductIdByName(String name);

    String saveProduct(Product product);
}
