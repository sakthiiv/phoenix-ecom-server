package com.phoenix.ecom.repository.category;

import com.phoenix.ecom.model.Category;

import java.util.List;

public interface ICategoryRepository {
    void saveCategory(Category category);

    List<Category> getAllCategories();
}
