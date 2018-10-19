package com.phoenix.ecom.service;

import com.mongodb.MongoException;
import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.repository.category.CategoryRepository;
import com.phoenix.ecom.service.category.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {


    @Mock
    private CategoryRepository categoryRepository;


    private  CategoryService categoryService;

    private Category category;

    @Before
    public void setUp() throws Exception {
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void shouldCreateCategory(){
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames);

        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);
        categoryService.createNewCategory(category);

        verify(categoryRepository, times(1)).saveCategory(category);


    }

    @Test(expected = MongoException.class)
    public void shouldThrowErrorIfTheRepositoryHasAnIssueInStoringCategory(){
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames);
        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        doThrow(MongoException.class).when(categoryRepository).saveCategory(ac.capture());

        categoryService.createNewCategory(category);

    }


    private Category initializeCategory(String categoryName, String description, List<String> subCategoryNames){
        Category category=new Category();
        category.setDescription(description);
        category.setName(categoryName);
        List<Category> subCategories=new ArrayList<>();
        for(String subCategoryName : subCategoryNames){
            Category subCategory=new Category();
            subCategory.setName(subCategoryName);
            subCategories.add(subCategory);
        }
        category.setSubCategory(subCategories);
        return category;
    }


}