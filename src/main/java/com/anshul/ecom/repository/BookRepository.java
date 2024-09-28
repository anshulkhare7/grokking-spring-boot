package com.anshul.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshul.ecom.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}