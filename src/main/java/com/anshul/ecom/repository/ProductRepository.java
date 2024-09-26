package com.anshul.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anshul.ecom.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
