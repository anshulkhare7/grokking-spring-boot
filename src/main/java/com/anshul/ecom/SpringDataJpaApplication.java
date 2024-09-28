package com.anshul.ecom;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.anshul.ecom.entity.Author;
import com.anshul.ecom.entity.Book;
import com.anshul.ecom.repository.AuthorRepository;
import com.anshul.ecom.repository.BookRepository;
    
    @SpringBootApplication
    public class SpringDataJpaApplication implements CommandLineRunner {
    
        private static final Logger log = LoggerFactory.getLogger(SpringDataJpaApplication.class);
    
        public static void main(String[] args) {
            SpringApplication.run(SpringDataJpaApplication.class, args).close();
        }
    
        @Autowired
        private BookRepository bookRepository;
    
        @Autowired
        private AuthorRepository authorRepository;
    
        @Override
        @Transactional
        public void run(String... args) throws Exception {
                        
            //create authors
            Author author1 = new Author();
            author1.setFirstName("Peter");
            author1.setLastName("Thiel");
            authorRepository.save(author1);
    
            Author author2 = new Author();
            author2.setFirstName("Blake");
            author2.setLastName("Masters");
            authorRepository.save(author2);
    
            //create book
            Book book = new Book();
            book.setName("Zero to one");
            book.setSummary("Notes on startups, or how to build the future");
    
            List<Author> authors = new ArrayList<>();
            authors.add(author1);
            authors.add(author2);
    
            //set authors to a book
            book.setAuthors(authors);
    
            List<Book> books = new ArrayList<>();
            books.add(book);
    
            //save book
            bookRepository.save(book);
    
            //set book to authors
            author1.setBooks(books);
            author2.setBooks(books);
    
            //update authors
            authorRepository.save(author1);
            authorRepository.save(author2);
    
            //read all books
            log.info(String.valueOf(bookRepository.findAll()));
    
            //read all authors
            log.info(String.valueOf(authorRepository.findAll()));
    
            //delete all books
            bookRepository.deleteAll(bookRepository.findAll());
    
            //delete all authors
            authorRepository.deleteAll(authorRepository.findAll());
        }
}
