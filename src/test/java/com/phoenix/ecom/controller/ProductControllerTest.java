/*
package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.service.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProductControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @MockBean
    private ProductService productService;

    @Test
    public void shouldCreateAProduct() throws Exception {

        String uri = "/api/v1/product";
        Product product = initializeProduct("samsung");
        String inputJson = super.mapToJson(product);

        this.mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string("Product created successfully"));

        ArgumentCaptor<Product> ac = ArgumentCaptor.forClass(Product.class);

        verify(productService, times(1)).createProduct(ac.capture());
        Product value = ac.getValue();
        assertEquals(product.getName(), value.getName());
    }
}




*/
