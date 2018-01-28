package edu.pw.platon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PlatonApplication {

	@Autowired
	public static WebMvcConfigurerAdapter forwardToIndex;

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(PlatonApplication.class, args);
		DriverManager.registerDriver(new org.h2.Driver());

	}
}
