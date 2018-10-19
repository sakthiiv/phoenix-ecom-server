package com.phoenix.ecom.controller;


import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /*@GetMapping("/category")
    public @ResponseBody ResponseEntity<List<Category>> displayAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }*/


//    @GetMapping("/category/{id}")
//    public ResponseEntity<String> displayCategory(){
//        return ResponseEntity.ok("read one category");
//    }


    @RequestMapping(value= "/category", method=RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createNewCategory(@RequestBody Category category){
        try{
            categoryService.createNewCategory(category);
            return new ResponseEntity("{ \"message\":\"Category created successfully\"}",HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity("{\"message\" : \"Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




//    @PutMapping("/category/{id}")
//    public ResponseEntity<Object> updateCategory(@RequestBody Category category, @PathVariable int id) {
//
//        return ResponseEntity.ok("Category updated successfully");
//    }

//    @DeleteMapping("/category/{id}")
//    public void deleteStudent(@PathVariable int id) {
//
//    }

    }
