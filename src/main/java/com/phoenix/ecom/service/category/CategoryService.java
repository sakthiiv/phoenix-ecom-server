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
        this.categoryRepository = categoryRepository;
    }

    public void createNewCategory(Category category){
        try{
            categoryRepository.saveCategory(category);
        }catch (MongoException e){
            throw new MongoException(e.getMessage());
        }
    }

    public void deleteCategory(Category category){

    }


    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.getAllCategories();
        return categories;
    }
}
