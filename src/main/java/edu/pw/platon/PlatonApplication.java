package edu.pw.platon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class PlatonApplication {

	@Autowired
	public static WebMvcConfigurerAdapter forwardToIndex;

	public static void main(String[] args) {
		SpringApplication.run(PlatonApplication.class, args);
	}
}
