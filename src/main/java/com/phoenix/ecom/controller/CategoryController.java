package com.phoenix.ecom.controller;


import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @CrossOrigin
    @GetMapping("/category")
    public @ResponseBody
    ResponseEntity<List<Category>> displayAllCategories() {
        try {
            return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("{\"message\":\"Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<String> displayCategory(@PathVariable String id) {
        try {
            return new ResponseEntity(categoryService.getCategoryById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("{\"message\":\"Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value="/category", method=RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> createNewCategory(@RequestBody Category category) {
        try {
            categoryService.createNewCategory(category);
            return new ResponseEntity("{ \"message\":\"Category created successfully\"}", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("{\"message\" : \"Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@RequestBody Category category) {
        try {
            categoryService.deleteCategory(category);
            return new ResponseEntity<>("{ \"message\":\"Category deleted successfully\"}", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("{\"message\" : \"Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/category/{id}")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
        try{
            categoryService.updateCategory(category);
            return ResponseEntity.ok("Category updated successfully");
        } catch (Exception e){
            return new ResponseEntity("{\"message\" : \"Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
