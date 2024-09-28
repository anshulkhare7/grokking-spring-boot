package com.anshul.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anshul.ecom.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}