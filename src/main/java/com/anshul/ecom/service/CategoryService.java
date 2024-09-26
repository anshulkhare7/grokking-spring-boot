package com.anshul.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.anshul.ecom.model.Category;

@Service
public class CategoryService {

    public List<Category> getAllCategories() {
        return new ArrayList<Category>();
    }

    public String createCategory (Category category) {
        return "Category created";
    }
}
