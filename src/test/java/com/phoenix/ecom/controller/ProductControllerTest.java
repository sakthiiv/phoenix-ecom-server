/*
package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Product;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ProductControllerTest extends AbstractTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createProduct() throws Exception {
        String uri = "/api/v1/product";
        Product product = new Product("iPad", 50000, "electronic", "tablet", "apple", true);

        String inputJson = super.mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);

        mongoTemplate.remove(new Query(Criteria.where("Id").is(new ObjectId(content))), Product.class);
    }
}




*/
