package com.phoenix.ecom.service.category;

import com.mongodb.MongoException;
import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.repository.category.CategoryRepository;
import com.phoenix.ecom.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository=categoryRepository;
    }

    public void createNewCategory(Category category) {
        try {
            categoryRepository.saveCategory(category);
        } catch (MongoException e) {
            throw new MongoException(e.getMessage());
        }
    }

    public void deleteCategory(String id){
        try {
            categoryRepository.deleteCategory(id);
        } catch (MongoException e){
            throw e;
        }
    }

    public List<Category> getAllCategories() throws Exception {
        try {
            return categoryRepository.getAllCategories();
        } catch (Exception e) {
            throw new Exception();
        }
    }


    public void updateCategory(Category category) {
        try {
            categoryRepository.updateCategory(category);
        } catch (Exception e) {
            throw e;
        }
    }

    public Category getCategoryById(String s) throws Exception {
                try {
                    return categoryRepository.findCategoryById(s);
                } catch (Exception e) {
                    throw new Exception();
                }
            }
}
