package com.anshul.ecom.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anshul.ecom.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}