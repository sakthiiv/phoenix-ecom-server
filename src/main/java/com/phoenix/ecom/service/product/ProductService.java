package com.phoenix.ecom.service.product;

import com.phoenix.ecom.model.Product;
import com.phoenix.ecom.repository.product.IProductRepository;
import com.phoenix.ecom.repository.product.ProductRepository;
import com.phoenix.ecom.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private IProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public CategoryService categoryService;

    public void createProduct(Product product) {
        productRepository.saveProduct(product);
    }

    public Product getProduct(String id) throws Exception {
        return productRepository.findProductById(id);
    }

    public List<Product> getProduct(String categoryId, String subCategoryId){
        return productRepository.findProductByCategoryIdAndSubCategoryId(categoryId, subCategoryId);
    }

    public List<Product> getAllProducts(){
        return productRepository.listAllProducts();
    }


    public List<Product> searchProducts(String searchString) throws Exception{
        try {
            List<Product> listOfAllProducts = getAllProducts();
            List<Product> filteredProductList = this.filterProducts(searchString.toLowerCase(), listOfAllProducts);
            return filteredProductList;
        }
        catch (Exception e){
            throw new Exception("Search Could not be completed.");
        }

    }

    private List<Product> filterProducts(String searchString,List<Product> listOfAllProducts) throws Exception{
        List<Product> filteredProductList = new ArrayList<Product>();
        for (Product product: listOfAllProducts) {
            String productName = product.getName().toLowerCase();
            String productDescription = product.getDescription().toLowerCase();
            String productCategoryName = categoryService.getCategoryById(product.getCategoryId()).getName();
            String subCategoryName = product.getSubCategoryId().toLowerCase();
            String categoryId = product.getCategoryId().toLowerCase();
            String productId = product.getId().toLowerCase();

            if(productName.contains(searchString)|| productDescription.contains(searchString)
                    || productCategoryName.contains(searchString) ||
                    subCategoryName.contains(searchString) ||categoryId.contains(searchString) || productId.contains(searchString)){

                filteredProductList.add(product);
            }

        }

        return filteredProductList;
    }


    public List<Product> getProductListByCategoryId(String categoryId){
        return productRepository.listAllProductsByCategoryID(categoryId);
    }


    public Product updateProduct(String id, Product productRequestToBeUpdated) throws Exception {
        Product product = getProduct(id);
        if (product != null) {
            if(productRequestToBeUpdated.getName() != null) {
                product.setName(productRequestToBeUpdated.getName());
            }
            if(productRequestToBeUpdated.getCategoryId() != null) {
                product.setCategoryId(productRequestToBeUpdated.getCategoryId());
            }
            if(productRequestToBeUpdated.getSubCategoryId() != null) {
                product.setSubCategoryId(productRequestToBeUpdated.getSubCategoryId());
            }
            if(productRequestToBeUpdated.getDescription() != null) {
                product.setDescription(productRequestToBeUpdated.getDescription());
            }
            if(productRequestToBeUpdated.getPrice() != 0) {
                product.setPrice(productRequestToBeUpdated.getPrice());
            }
            return productRepository.updateProduct(id, product);
        }else {
            throw new Exception("Product to be updated not found");
        }
    }

    public void deleteProduct(String id) throws Exception {
        Product product = getProduct(id);
        if(product != null) {
            productRepository.deleteProduct(product);
        }else{
            throw new Exception("Product to be deleted not found");
        }
    }
}