package com.phoenix.ecom.service;

import com.phoenix.ecom.model.Cart;
import com.phoenix.ecom.repository.cart.CartRepository;
import com.phoenix.ecom.service.cart.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    private CartService cartService;

    @Before
    public void setUp() throws Exception {
        cartService = new CartService(cartRepository);
    }

    @Test
    public void shouldCreateCartEntry(){

        Cart cart = mock(Cart.class);
        cartService.createCart(cart);

        verify(cartRepository, times(1)).addProduct(cart);
    }

}
