package com.phoenix.ecom.service.product;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.repository.product.IProductRepository;
import com.phoenix.ecom.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private IProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
        productRepository.saveProduct(product);
    }

    public Product getProduct(String id) throws Exception {
        return productRepository.findProductById(id);
    }

    public List<Product> getProduct(String categoryId, String subCategoryId){
        return productRepository.findProductByCategoryIdAndSubCategoryId(categoryId, subCategoryId);
    }

    public Product updateProduct(String id, Product productRequestToBeUpdated) throws Exception {
        Product product = getProduct(id);
        if (product != null) {
            if(productRequestToBeUpdated.getName() != null) {
                product.setName(productRequestToBeUpdated.getName());
            }
            if(productRequestToBeUpdated.getCategoryId() != null) {
                product.setCategoryId(productRequestToBeUpdated.getCategoryId());
            }
            if(productRequestToBeUpdated.getSubCategoryId() != null) {
                product.setSubCategoryId(productRequestToBeUpdated.getSubCategoryId());
            }
            if(productRequestToBeUpdated.getDescription() != null) {
                product.setDescription(productRequestToBeUpdated.getDescription());
            }
            if(productRequestToBeUpdated.getPrice() != 0) {
                product.setPrice(productRequestToBeUpdated.getPrice());
            }
            return productRepository.updateProduct(id, product);
        }else {
            throw new Exception("Product to be updated not found");
        }
    }

    public void deleteProduct(String id) throws Exception {
        Product product = getProduct(id);
        if(product != null) {
            productRepository.deleteProduct(product);
        }else{
            throw new Exception("Product to be deleted not found");
        }
    }
}