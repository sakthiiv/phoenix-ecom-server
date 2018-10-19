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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {


    @Mock
    private CategoryRepository categoryRepository;


    private CategoryService categoryService;

    private Category category;

    @Before
    public void setUp() throws Exception {
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void shouldCreateCategory() {
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames, "");

        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);
        categoryService.createNewCategory(category);

        verify(categoryRepository, times(1)).saveCategory(category);


    }

    @Test(expected = MongoException.class)
    public void shouldThrowErrorIfTheRepositoryHasAnIssueInStoringCategory() {
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames, "");
        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        doThrow(MongoException.class).when(categoryRepository).saveCategory(ac.capture());

        categoryService.createNewCategory(category);

    }


    @Test
    public void shouldRetrieveAllCategories() throws Exception {
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames, "");
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        when(categoryRepository.getAllCategories()).thenReturn(categories);

        assertEquals(categoryService.getAllCategories().get(0).getName(), category.getName());

    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenTryingToRetrieveAllCategories() {
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames, "");
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        when(categoryRepository.getAllCategories()).thenThrow(Exception.class);

    }

    @Test
    public void shouldDeleteASpecificCategory() {
        String categoryId = "123";
        List subCategoryNames = new ArrayList<String>();
        Category category = initializeCategory("", "", subCategoryNames, categoryId);
        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        categoryService.deleteCategory(category);

        verify(categoryRepository, times(1)).deleteCategory(categoryId);
    }

    @Test(expected = MongoException.class)
    public void shouldThrowAnExceptionIfThereIsAnErrorInTryingToDeleteACategoryByTheRepository() {
        String categoryId = "123";
        List subCategoryNames = new ArrayList<String>();
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames, categoryId);
        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        doThrow(MongoException.class).when(categoryRepository).deleteCategory(categoryId);

        categoryService.deleteCategory(category);

    }

    @Test
    public void shouldUpdateASpecificCategory() {
        String categoryId = "123";
        List subCategoryNames = new ArrayList<String>();
        Category category = initializeCategory("", "", subCategoryNames, categoryId);
        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        categoryService.updateCategory(category);

        verify(categoryRepository, times(1)).updateCategory(category);
    }

    @Test(expected = MongoException.class)
    public void shouldThrowAnExceptionIfThereIsAnErrorInTryingToUpdateACategoryByTheRepository() {
        String categoryId = "123";
        List subCategoryNames = new ArrayList<String>();
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames, categoryId);
        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        doThrow(MongoException.class).when(categoryRepository).updateCategory(ac.capture());

        categoryService.updateCategory(category);

    }

    private Category initializeCategory(String categoryName, String description, List<String> subCategoryNames, String id) {
        Category category = new Category();
        category.setId(id);
        category.setDescription(description);
        category.setName(categoryName);
        List<Category> subCategories = new ArrayList<>();
        for (String subCategoryName : subCategoryNames) {
            Category subCategory = new Category();
            subCategory.setName(subCategoryName);
            subCategories.add(subCategory);
        }
        category.setSubCategory(subCategories);
        return category;
    }


}
