package com.phoenix.ecom.repository.user;

import com.phoenix.ecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository{
    private static final String collectionName = "users";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void SaveUser(User user) {
        user.setValid(true);
        mongoTemplate.insert(user, collectionName);
    }
}
