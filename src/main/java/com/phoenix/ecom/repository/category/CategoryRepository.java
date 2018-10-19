package com.phoenix.ecom.repository.category;

import com.phoenix.ecom.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository implements ICategoryRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveCategory(Category category) {
        mongoTemplate.insert(category, "category");
    }
}