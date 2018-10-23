package com.phoenix.ecom.repository.user;

import com.phoenix.ecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public boolean VerifyUserAlreadyExists(User user) {
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("userName").is(user.getUserName()),Criteria.where("emailId").is(user.getEmailId()));
        Query query = new Query(criteria);
        List<User> users = mongoTemplate.find(query, User.class, collectionName);
        if(users.size()> 0){
            return true;
        }
        return false;


    }
}
