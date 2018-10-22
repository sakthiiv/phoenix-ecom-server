package com.phoenix.ecom.repository.user;

import com.phoenix.ecom.model.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository implements IAdminRepository {
    private static final String collectionName = "admin";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void SaveUser(Admin admin) {

        mongoTemplate.insert(admin, collectionName);
    }
}
