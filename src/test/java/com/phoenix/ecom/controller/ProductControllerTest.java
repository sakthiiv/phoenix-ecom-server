package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.service.product.ProductService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        String inputJson = super.mapToJsonString(product);

        this.mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"Product created successfully\"")));

        ArgumentCaptor<Product> ac = ArgumentCaptor.forClass(Product.class);

        verify(productService, times(1)).createProduct(ac.capture());
        Product value = ac.getValue();
        assertEquals(product.getName(), value.getName());
    }

    @Test
    public void shouldRetrieveAProduct() throws Exception {

        String uri = "/api/v1/product/{id}";
        Product product = initializeProduct("samsung");
        product.setId("1234");
        String inputJson = super.mapToJsonString(product);

        when(productService.getProduct(product.getId())).thenReturn(product);

        this.mvc.perform(get(uri, product.getId()).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(inputJson));
    }


//    @Test
//    public void shouldRetrieveListOfProductsMatchingASearchString() throws Exception{
//        String uri = "/api/v1/search/{q}";
//        Product product = initializeProduct("samsung");
//        product.setId("1234");
//        String inputJson = super.mapToJsonString(product);
//        List<Product> filteredProductList = new ArrayList<Product>();
//        filteredProductList.add(product);
//
//        when(productService.searchProducts("samsung")).thenReturn(filteredProductList);
//
//        this.mvc.perform(get(uri, "samsung").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().json(inputJson));
//
//    }

    @Test
    public void shouldRetrieveAllProduct() throws Exception {

        String uri = "/api/v1/product";
        Product product = initializeProduct("sony");
        Product product2 = initializeProduct("Samsung");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product2);

        String inputJson = super.mapToJsonString(productList);

        when(productService.getProduct(null, null)).thenReturn(productList);

        this.mvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(inputJson));
    }

    //TODO - fix this test
    @Ignore
    public void shouldUpdateAProduct() throws Exception {

        Product product = mock(Product.class);
        Product productOutput = mock(Product.class);
        productOutput.setId("1234");

        String uri = "/api/v1/product/1234";
        when(productService.updateProduct("1234", product)).thenReturn(productOutput);

        this.mvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(mapToJsonString(product))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(mapToJsonString(productOutput)));

    }

    @Test
    public void shouldDeleteAProduct() throws Exception {
        String uri = "/api/v1/product/{id}";
        String id = "1234";

        this.mvc.perform(delete(uri, id).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"Product deleted successfully\"")));

        verify(productService, times(1)).deleteProduct(id);
    }
}
