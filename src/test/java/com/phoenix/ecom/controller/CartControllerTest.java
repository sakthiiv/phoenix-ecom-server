package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Cart;
import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.service.cart.CartService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CartControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @MockBean
    private CartService cartService;

    @Test
    public void shouldAddProductToCart() throws Exception {

        String uri = "/api/v1/cart";
        Product product = initializeProduct("samsung");
        Cart cart = initializeCart("123", Arrays.asList(product));

        String inputJson = super.mapToJsonString(cart);

        this.mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(inputJson)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"Product added to cart successfully\"")));

        ArgumentCaptor<Cart> ac = ArgumentCaptor.forClass(Cart.class);

        verify(cartService, times(1)).createCart(ac.capture());
    }


}
