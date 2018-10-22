package com.phoenix.ecom.repository.product;

import com.phoenix.ecom.model.Image;
import com.phoenix.ecom.model.Product;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements IProductRepository{

    @Autowired
    MongoTemplate mongoTemplate;

    public void saveProduct(Product product) {
        mongoTemplate.insert(product, "product");

        Product productSaved = mongoTemplate.findOne(new Query(Criteria.where("name").is(product.getName())), Product.class);

        Image image = new Image(productSaved.getId(), product.getImageContent());
        mongoTemplate.insert(image, "image");
    }

    @Override
    public Product findProductById(String id) throws Exception {
        Product product = mongoTemplate.findById(new ObjectId(id), Product.class);
        if(product == null){
            throw new Exception("Product with id" +id+ " not found");
        }
        return product;
    }

    @Override
    public List<Product> findProductByCategoryIdAndSubCategoryId(String categoryId, String subCategoryId) {
        List<Product> products;

        if(categoryId != null && subCategoryId != null){
            products = mongoTemplate.find(new Query(Criteria.where("categoryId").is(categoryId).and("subCategoryId").is(subCategoryId)), Product.class);
        }else if (categoryId != null){
            products = mongoTemplate.find(new Query(Criteria.where("categoryId").is(categoryId)), Product.class);
        }else if(subCategoryId != null){
            products = mongoTemplate.find(new Query(Criteria.where("subCategoryId").is(subCategoryId)), Product.class);
        }else{
            products = mongoTemplate.findAll(Product.class);
        }

        return products;
    }

    @Override
    public Product updateProduct(String id, Product product) {
        mongoTemplate.save(product, "product");
        Product productUpdated = mongoTemplate.findById(new ObjectId(id), Product.class);
        return productUpdated;
    }

    @Override
    public void deleteProduct(Product product) {
        mongoTemplate.remove(product, "product");
    }


    @Override
    public List<Product> listAllProducts() {

        return findProductByCategoryIdAndSubCategoryId(null,null);
    }


    @Override
    public List<Product> listAllProductsByCategoryID(String categoryId){
        return findProductByCategoryIdAndSubCategoryId(categoryId,null);
    }

}
