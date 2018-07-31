package com.erivan.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.erivan.cursomc.services.DBService;
import com.erivan.cursomc.services.EmailService;
import com.erivan.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	public DBService dbService; 
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	public String strategy;
	
	@Bean
	public boolean instantianteDataBase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
