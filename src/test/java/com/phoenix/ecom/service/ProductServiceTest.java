package com.phoenix.ecom.service;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.repository.product.ProductRepository;
import com.phoenix.ecom.service.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductService(productRepository);
    }

    @Test
    public void shouldCreateService(){

        Product product = mock(Product.class);
        productService.createProduct(product);

        verify(productRepository, times(1)).saveProduct(product);
    }

}
