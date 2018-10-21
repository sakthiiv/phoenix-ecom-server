package com.phoenix.ecom.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.ecom.EcomApplication;
import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.model.Product;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EcomApplication.class)
@WebAppConfiguration
public abstract class AbstractTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected static String mapToJsonString(final Object obj) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String res = objectMapper.writeValueAsString(obj);
        return res;
    }

    protected Category initializeCategory(String categoryName, String description, List<String> subCategoryNames,String categoryId){
        Category category=new Category();
        category.setId(categoryId);
        category.setDescription(description);
        category.setName(categoryName);
        List<Category> subCategories=new ArrayList<>();
        for(String subCategoryName : subCategoryNames){
            Category subCategory=new Category();
            subCategory.setName(subCategoryName);
            subCategories.add(subCategory);
        }
        category.setSubCategory(subCategories);
        return category;
    }

    protected Product initializeProduct(String productName){
        Product product = new Product(productName, 200, "Category", "SubCategory", "Description", "fgdh");
        return product;
    }
}
