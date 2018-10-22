package com.phoenix.ecom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO: Remove Abstract Test
public class CategoryControllerTest extends AbstractTest{

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @MockBean
    private CategoryService categoryService;


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper=new ObjectMapper();
            final String jsonContent=mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

  @Test
    public void shouldGetAllCategories() throws Exception {
       List subCategoryNames = new ArrayList<String>();
       subCategoryNames.add("sub_electronics");
       Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames,"");
       List<Category> categories = new ArrayList<>();
       categories.add(category);

       when(categoryService.getAllCategories()).thenReturn(categories);

       this.mvc.perform(get("/api/v1/category").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category)))
               .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldThrowServerErrorWhenTryingToGetAllCategories() throws Exception {
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames,"");

        when(categoryService.getAllCategories()).thenThrow(Exception.class);

        this.mvc.perform(get("/api/v1/category").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category))).andExpect(status().is5xxServerError()).andDo(print())
                .andExpect(content().string(containsString("Server Error")));;
    }

    @Test
    public void shouldGetCategoryCorrespondingToPassedId() throws Exception{
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames,"1234");

        when(categoryService.getCategoryById("1234")).thenReturn(category);

        this.mvc.perform(get("/api/v1/category/1234").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category)))
                .andExpect(status().isOk()).andDo(print());

    }

    @Test
    public void shouldThrowErrorWhenTryingToGetCategoryById() throws Exception{
        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames,"1234");

        when(categoryService.getCategoryById("1234")).thenThrow(Exception.class);

        this.mvc.perform(get("/api/v1/category/1234").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category))).andDo(print())
                .andExpect(status().is5xxServerError()).andExpect(content().string(containsString("Server Error")));
    }

    @Test
    public void shouldCreateACategory() throws Exception {

        List subCategoryNames = new ArrayList<String>();
        subCategoryNames.add("sub_electronics");
            Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames,"");
        String inputJson = super.mapToJsonString(category);

        this.mvc.perform(post("/api/v1/category").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category))).andDo(print()).andExpect(status().isCreated())
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
        String inputJson = super.mapToJsonString(category);

        doThrow(IllegalArgumentException.class).when(categoryService).createNewCategory(ac.capture());

        this.mvc.perform(post("/api/v1/category").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category))).andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Server Error")));

    }

    @Test
    public void shouldDeleteTheSpecificCategory() throws Exception {
        String id  = "123";


        this.mvc.perform(delete("/api/v1/category/123").contentType(MediaType.APPLICATION_JSON).content(id)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Category deleted successfully")));


        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);

        verify(categoryService, times(1)).deleteCategory(ac.capture());
        String value = ac.getValue();
        assertEquals("123",value);

    }

    @Test
    public void shouldReturnInternalServerErrorIfThereIsAnIssueWithTheServiceDeletingTheCategoryId() throws Exception {
        String id  = "123";
        List subCategoryNames = new ArrayList<String>();
        Category category = initializeCategory("", "", subCategoryNames,id);
        ArgumentCaptor<String> ac = ArgumentCaptor.forClass(String.class);
        String inputJson = super.mapToJsonString(category);

        doThrow(IllegalArgumentException.class).when(categoryService).deleteCategory(ac.capture());

        this.mvc.perform(delete("/api/v1/category/123").contentType(MediaType.APPLICATION_JSON).content(inputJson)).andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Server Error")));
    }

    @Test
    public void shouldUpdateTheCategoryBasedOnCategoryId() throws Exception {
        String id = "123";
        List subCategoryNames = new ArrayList<String>();
        Category category = initializeCategory("", "Category Description", subCategoryNames,id);
        String inputJson = super.mapToJsonString(category);

        this.mvc.perform(put("/api/v1/category/123").contentType(MediaType.APPLICATION_JSON).content(inputJson)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Category updated successfully")));


        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        verify(categoryService, times(1)).updateCategory(ac.capture());
        Category value = ac.getValue();
        assertEquals("123",value.getId());
        assertEquals("Category Description",value.getDescription());

    }

    @Test
    public void shouldReturnInternalServerErrorIfThereIsAnIssueWithTheServiceUpdatingTheCategoryId() throws Exception {
        String id  = "123";
        List subCategoryNames = new ArrayList<String>();
        Category category = initializeCategory("", "", subCategoryNames,id);
        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);
        String inputJson = super.mapToJsonString(category);

        doThrow(IllegalArgumentException.class).when(categoryService).updateCategory(ac.capture());

        this.mvc.perform(put("/api/v1/category/123").contentType(MediaType.APPLICATION_JSON).content(inputJson)).andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Server Error")));
    }

}
