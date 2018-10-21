package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/product" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Product productRequest){
        productService.createProduct(productRequest);
        return new ResponseEntity<>("{ \"message\":\"Product created successfully\"}", HttpStatus.CREATED);
    }

    @GetMapping(value = "/product/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> retrieve(@PathVariable("id") String id) throws Exception{
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/product" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> retrieveAll(@RequestParam(required = false) String categoryId,
                                                     @RequestParam(required = false) String subCategoryId) {
        List<Product> products = productService.getProduct(categoryId, subCategoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/product/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> update(@PathVariable("id") String id,
                                               @RequestBody Product productRequestToBeUpdated) throws Exception {
        Product product = productService.updateProduct(id, productRequestToBeUpdated);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "/product/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable("id") String id) throws Exception {
        productService.deleteProduct(id);
        return new ResponseEntity<>("{ \"message\":\"Product deleted successfully\"}", HttpStatus.OK);
    }

}
