package com.anshul.ecom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.anshul.ecom.entity.Author;
import com.anshul.ecom.entity.Book;
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
        log.info("CRUD features started");

        log.info("CREATE");

        Book egoIsTheEnemy = new Book();
        egoIsTheEnemy.setName("Ego is the enemy");
        egoIsTheEnemy.setSummary("The fight to master our greatest opponent");
        bookRepository.save(egoIsTheEnemy);
        
        Author ryanHoliday = new Author();
        ryanHoliday.setFirstName("Ryan");
        ryanHoliday.setLastName("Holiday");
        authorRepository.save(ryanHoliday);

        log.info("READ");
        
        log.info(String.valueOf(bookRepository.findById(egoIsTheEnemy.getId())));
        log.info(String.valueOf(authorRepository.findById(ryanHoliday.getId())));

        log.info("UDPATE");
        
        egoIsTheEnemy.setSummary("The fight to master our greatest opponent from Ryan Holiday");
        bookRepository.save(egoIsTheEnemy);

        ryanHoliday.setLastName("Holi");
        authorRepository.save(ryanHoliday);

        log.info("DELETE");
        
        bookRepository.delete(egoIsTheEnemy);
        authorRepository.delete(ryanHoliday);

        log.info("CRUD features done");
    }
}
