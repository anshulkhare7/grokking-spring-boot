package com.anshul.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anshul.ecom.model.Product;
import com.anshul.ecom.repository.ProductRepository;

@Service
public class ProductService {
    
     @Autowired
    ProductRepository repo;

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Product getProductById(String prodId) {
        return repo.findById(prodId).orElse(new Product());
    }

    public List<Product> getProductByCategory(String category) {
        return repo.findByCategory(category);
    }

    public void addProduct(Product prod) {
        repo.save(prod);
    }

    public Product updateProduct(Product prod) {
        Product existingProd = repo.findById(prod.getId()).get();
        existingProd.setCategory(prod.getCategory());
        existingProd.setPrice(prod.getPrice());
        existingProd.setProdName(prod.getProdName());
        return repo.save(prod);
    }

    public void deleteProduct(String prodId) {
        repo.deleteById(prodId);
    }

}
