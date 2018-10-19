package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.service.category.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @MockBean
    private CategoryService categoryService;

   /* @Test
    public void shouldGetAllCategories() throws Exception {\
        Category category=new Category();
        category.setDescription("Electronics");
        category.setName("El");
        Category phone=new Category();
        phone.setName("Phone");
        List<Category> subCategories=new ArrayList<>();
        subCategories.add(phone);
        category.setSubCategory(subCategories);
        when(categoryService.getAllCategories()).thenReturn()
        this.mvc.perform(get("/category")).andExpect(status().isOk());
    }*/

    @Test
    public void shouldCreateACategory() throws Exception {

        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
            Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames,"");
        String inputJson = super.mapToJson(category);

        this.mvc.perform(post("/api/v1/category").contentType(MediaType.APPLICATION_JSON).content(inputJson)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("Category created successfully")));


        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        verify(categoryService, times(1)).createNewCategory(ac.capture());
        Category value = ac.getValue();
        assertEquals("Descriptions for electronics",value.getDescription());

    }

    @Test
    public void shouldReturnInternalServerError() throws Exception {
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames,"");
        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);
        String inputJson = super.mapToJson(category);

        doThrow(IllegalArgumentException.class).when(categoryService).createNewCategory(ac.capture());

        this.mvc.perform(post("/api/v1/category").contentType(MediaType.APPLICATION_JSON).content(inputJson)).andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Server Error")));

    }

    @Test
    public void shouldDeleteTheSpecificCategory() throws Exception {
        String id  = "123";
        List subCategoryNames = new ArrayList<String>();
        Category category = initializeCategory("", "", subCategoryNames,id);

        String inputJson = super.mapToJson(category);

        this.mvc.perform(delete("/api/v1/category/123").contentType(MediaType.APPLICATION_JSON).content(inputJson)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Category deleted successfully")));


        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        verify(categoryService, times(1)).deleteCategory(ac.capture());
        Category value = ac.getValue();
        assertEquals("123",value.getId());

    }



}
