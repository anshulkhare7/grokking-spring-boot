package com.anshul.ecom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.anshul.ecom.model.Category;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CategoryController {

    @GetMapping("/categories")    
    public List<Category> getAllCategories() {
        return new ArrayList<Category>();
    }
}
