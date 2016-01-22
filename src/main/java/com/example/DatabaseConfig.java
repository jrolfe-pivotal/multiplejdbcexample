package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfig {

	@Primary
	@Bean(name="datasourceA")
	@ConfigurationProperties(prefix="spring.datasource.A")
	public DataSource primaryDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Bean(name="datasourceB")
	@ConfigurationProperties(prefix="spring.datasource.B")
	@Qualifier("datasource.B")
	public DataSource secondaryDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
	@Autowired
	@Bean(name="jdbcTemplateA")
	public JdbcTemplate jdbcTemplateA(@Qualifier("datasourceA") DataSource dsA) {
		return new JdbcTemplate(dsA);
	}
	
	@Autowired
	@Bean(name="jdbcTemplateB")
	public JdbcTemplate jdbcTemplateB(@Qualifier("datasourceB") DataSource dsB) {
		return new JdbcTemplate(dsB);
	}
}