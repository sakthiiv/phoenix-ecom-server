package com.phoenix.ecom.controller;


import com.phoenix.ecom.service.checkout.CheckoutService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CheckoutControllerTest extends AbstractTest{

    @Before
    @Override
    public void setUp(){
        super.setUp();
    }

    @MockBean
    private CheckoutService checkoutService;

    @Test
    public void shouldCheckoutAndGiveOrderId() throws Exception {
        String userId = "1234";
        String address = "wallstreet, silicon Valley, Bengaluru";
        String orderId = "12345";

        when(checkoutService.checkout(userId, address)).thenReturn(orderId);

        this.mvc.perform(post("/api/v1/checkout/" + userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(address))
                .andExpect(status().isOk())
                .andExpect(content().string("{ \"message\":\"Order placed successfully with Order Id: " + orderId +"\"}"));

    }
}
