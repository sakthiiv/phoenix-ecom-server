package com.phoenix.ecom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.service.category.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {


    @Autowired
    private MockMvc mvc;

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
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames);

        this.mvc.perform(post("/category").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category))).andDo(print()).andExpect(status().isOk())
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
        Category category = initializeCategory("Electronics", "Descriptions for electronics", subCategoryNames);
        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        doThrow(IllegalArgumentException.class).when(categoryService).createNewCategory(ac.capture());

        this.mvc.perform(post("/category").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category))).andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("Server Error")));

    }

    private Category initializeCategory(String categoryName,String description,List<String> subCategoryNames){
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
