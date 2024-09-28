package com.anshul.ecom;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringDataJDBCApplication implements CommandLineRunner {

	@Autowired
    JdbcTemplate jdbcTemplate;

	private static final Logger log = LoggerFactory.getLogger(SpringDataJDBCApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJDBCApplication.class, args);
	}

	@Override
    public void run(String... strings) throws Exception {
        List bookList = jdbcTemplate.queryForList("select * from book order by id desc");;
        log.info(String.valueOf(bookList));

        List authorList = jdbcTemplate.queryForList("select * from author order by id desc");;
        log.info(String.valueOf(authorList));

        
    }

    }
