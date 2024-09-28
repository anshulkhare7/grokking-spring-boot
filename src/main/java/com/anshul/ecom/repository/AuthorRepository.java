package com.anshul.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshul.ecom.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
