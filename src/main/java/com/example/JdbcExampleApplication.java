package com.example;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JdbcExampleApplication {
	
	private static final String TEST_QUERY="SHOW TABLES;";
	
	@Autowired
	@Qualifier("jdbcTemplateA")
	JdbcTemplate jdbcTemplateA;
	
	@Autowired
	@Qualifier("jdbcTemplateB")
	JdbcTemplate jdbcTemplateB;
	
	public static void main(String[] args) {
		SpringApplication.run(JdbcExampleApplication.class, args);
	}
	
	@PostConstruct
	public void testConnection() {
		System.out.println("===============AAAAAAA=================");
		System.out.println(jdbcTemplateA.queryForList(TEST_QUERY));
		System.out.println("===============AAAAAAA=================");
		System.out.println("===============BBBBBBB=================");
		System.out.println(jdbcTemplateB.queryForList(TEST_QUERY));
		System.out.println("===============BBBBBBB=================");
	}
}
