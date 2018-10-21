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
    public void shouldCreateProduct(){

        Product product = mock(Product.class);
        productService.createProduct(product);

        verify(productRepository, times(1)).saveProduct(product);
    }

    @Test
    public void shouldRetrieveProduct() throws Exception {

        Product product = mock(Product.class);
        productService.getProduct(product.getId());

        verify(productRepository, times(1)).findProductById(product.getId());
    }

    @Test
    public void shouldRetrieveAllProducts() throws Exception {

        productService.getProduct(null, null);

        verify(productRepository, times(1)).findProductByCategoryIdAndSubCategoryId(null, null);
    }

    @Test
    public void shouldRetrieveAllProductsForCategoryId() throws Exception {

        Product product = mock(Product.class);
        productService.getProduct(product.getCategoryId(), null);

        verify(productRepository, times(1)).findProductByCategoryIdAndSubCategoryId(product.getCategoryId(), null);
    }

    @Test
    public void shouldRetrieveAllProductsForSubCategoryId() throws Exception {

        Product product = mock(Product.class);
        productService.getProduct(null, product.getSubCategoryId());

        verify(productRepository, times(1)).findProductByCategoryIdAndSubCategoryId(null, product.getSubCategoryId());
    }

    @Test
    public void shouldRetrieveAllProductsForGivenCategoryIdAndSubCategoryId() throws Exception {

        Product product = mock(Product.class);
        productService.getProduct(product.getCategoryId(), product.getSubCategoryId());

        verify(productRepository, times(1)).findProductByCategoryIdAndSubCategoryId(product.getCategoryId(), product.getSubCategoryId());
    }

    @Test
    public void shouldUpdateProduct() throws Exception {

        Product product = mock(Product.class);
        String id = "1234";
        when(productService.getProduct(id)).thenReturn(product);
        productService.updateProduct(id, product);

        verify(productRepository, times(1)).updateProduct(id,product);
    }

    @Test
    public void shouldDeleteProduct() throws Exception {

        Product product = mock(Product.class);
        when(productService.getProduct(product.getId())).thenReturn(product);
        productService.deleteProduct(product.getId());

        verify(productRepository, times(1)).deleteProduct(product);
    }

}
