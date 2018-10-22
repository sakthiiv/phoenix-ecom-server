package com.phoenix.ecom.repository.product;

import com.phoenix.ecom.model.Product;

import java.util.List;

public interface IProductRepository {

    void saveProduct(Product product);

    Product findProductById(String id) throws Exception;

    List<Product> findProductByCategoryIdAndSubCategoryId(String categoryId, String subCategoryId);

    Product updateProduct(String id, Product product);

    void deleteProduct(Product product);

     List<Product> listAllProducts();

    List<Product> listAllProductsByCategoryID(String categoryId);
}
