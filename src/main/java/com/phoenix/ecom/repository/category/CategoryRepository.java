package com.phoenix.ecom.repository.category;

import com.phoenix.ecom.model.Category;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository implements ICategoryRepository {
    private static final String collectionName = "category";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveCategory(Category category) {
        mongoTemplate.insert(category, collectionName);
    }

    @Override
    public List<Category> getAllCategories() {
        return mongoTemplate.findAll(Category.class);
    }

    @Override
    public void deleteCategory(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
        mongoTemplate.findAndRemove(query,Category.class,collectionName);
    }
}
