package com.phoenix.ecom.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.ecom.controller.CategoryController;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    public void testGet() throws Exception {
        this.mvc.perform(get("/category")).andExpect(status().isOk());
    }

    @Test
    public void shouldCreateACategory() throws Exception {
        Category category=new Category();
        category.setDescription("Electronics");
        category.setName("El");
        Category phone=new Category();
        phone.setName("Phone");
        List<Category> subCategories=new ArrayList<>();
        subCategories.add(phone);
        category.setSubCategory(subCategories);

        this.mvc.perform(post("/category").contentType(MediaType.APPLICATION_JSON).content(asJsonString(category))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Category created successfully")));


        ArgumentCaptor<Category> ac = ArgumentCaptor.forClass(Category.class);

        verify(categoryService, times(1)).createNewCategory(ac.capture());
        Category value = ac.getValue();
        assertEquals("Electronics",value.getDescription());

    }


}
