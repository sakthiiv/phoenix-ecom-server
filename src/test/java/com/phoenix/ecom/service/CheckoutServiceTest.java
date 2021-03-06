package com.phoenix.ecom.service;

import com.phoenix.ecom.model.Cart;
import com.phoenix.ecom.model.CheckoutOrder;
import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.repository.cart.CartRepository;
import com.phoenix.ecom.repository.checkout.CheckoutRepository;
import com.phoenix.ecom.service.checkout.CheckoutService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

    @Mock
    private CheckoutRepository checkoutRepository;

    @Mock
    private CartRepository cartRepository;

    private CheckoutService checkoutService;

    @Before
    public void setUp(){
        checkoutService = new CheckoutService(cartRepository, checkoutRepository);
    }

    @Test
    public void shouldCheckoutOrder() {

        String userId = "123";
        String address = "Bangalore";
        Product product = mock(Product.class);
        Cart cart = mock(Cart.class);

        checkoutService.checkout(userId, address);

        when(cartRepository.getCart(userId)).thenReturn(cart);
        when(cart.getProducts()).thenReturn(Arrays.asList(product));

        CheckoutOrder order = mock(CheckoutOrder.class);
        when(checkoutRepository.checkout(order)).thenReturn("145");

    }

}
