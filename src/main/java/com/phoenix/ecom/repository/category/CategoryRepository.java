package com.phoenix.ecom.repository.category;

import com.phoenix.ecom.model.Category;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class CategoryRepository implements ICategoryRepository {
    private static final String collectionName = "category";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void saveCategory(Category category) {
        for (Category subCategory:category.getSubCategory()) {
            subCategory.setId(new ObjectId().toString());
        }
        mongoTemplate.insert(category, collectionName
        );
    }

    @Override
    public List<Category> getAllCategories() {
        Query query = new Query();
        query.addCriteria(Criteria.where("IsValid").is(true));
        return mongoTemplate.find(query,Category.class);
    }

    @Override
    public void deleteCategory(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));

        Update updatequery = new Update();
        updatequery.set("IsValid",false);
        mongoTemplate.findAndModify(query,updatequery,Category.class,collectionName);
    }


    @Override
    public void updateCategory(Category category) {
        Query findQuery = new Query();
        Update updateQuery = new Update();
        for (Category subCategory:category.getSubCategory()) {
            if(StringUtils.isEmpty(subCategory.getId())){
                subCategory.setId(new ObjectId().toString());
            }
        }
        updateQuery.set("description",category.getDescription());
        updateQuery.set("name",category.getName());
        updateQuery.set("subCategory",category.getSubCategory());
        findQuery.addCriteria(Criteria.where("_id").is(new ObjectId(category.getId())));

        mongoTemplate.findAndModify(findQuery,updateQuery,Category.class,collectionName);
    }


    @Override
    public Category findCategoryById(String id) {
        return mongoTemplate.findById(new ObjectId(id),Category.class,collectionName);
    }


}
