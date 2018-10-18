package com.phoenix.ecom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProductController.class })
@WebAppConfiguration
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreationOfANewproductSucceeds() throws Exception {
        //Product product = new Product();
        //String json = objectMapper.;

        /*mockMvc.perform(
                post("/product")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());*/
    }

}

