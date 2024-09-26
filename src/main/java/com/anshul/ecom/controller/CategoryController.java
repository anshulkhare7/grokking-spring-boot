package com.anshul.ecom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.anshul.ecom.model.Category;
import com.anshul.ecom.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")    
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/category")    
    public String createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
}
