package com.anshul.ecom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.anshul.ecom.repository.AuthorRepository;
import com.anshul.ecom.repository.BookRepository;

@SpringBootApplication
public class SpringDataJDBCApplication implements CommandLineRunner {

	@Autowired
    JdbcTemplate jdbcTemplate;

	private static final Logger log = LoggerFactory.getLogger(SpringDataJDBCApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJDBCApplication.class, args);
	}

	@Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public void run(String... strings) throws Exception {
        log.info("find all books");

        log.info(String.valueOf(bookRepository.findAll()));

        log.info("find all authors");

        log.info(String.valueOf(authorRepository.findAll()));
    }
}
