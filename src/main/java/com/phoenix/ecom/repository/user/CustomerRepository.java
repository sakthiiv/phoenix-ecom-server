package com.phoenix.ecom.repository.user;

import com.phoenix.ecom.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class CustomerRepository implements ICustomerRepository {
    private static final String collectionName = "customer";

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void SaveUser(Customer customer) {

        mongoTemplate.insert(customer, collectionName);
    }


}
