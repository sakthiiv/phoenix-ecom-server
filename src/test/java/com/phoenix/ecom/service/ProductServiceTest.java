package com.phoenix.ecom.service;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.repository.product.ProductRepository;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;


public class ProductServiceTest {

    @Test
    public void shouldCreateService(){

        Product product = mock(Product.class);
        ProductRepository repository = mock(ProductRepository.class);

        Mockito.when(repository.saveProduct(product)).thenReturn("123");
    }

}
