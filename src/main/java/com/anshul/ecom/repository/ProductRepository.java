package com.anshul.ecom.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.anshul.ecom.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategory(String category);

}
