package com.rbs.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rbs.os.service.DBService;

@Configuration
@Profile("teste")
public class TesteConfig {
	
	@Autowired
	private DBService dbService;

	@Bean
	public void instanciaDb() {
		
	dbService.instanciaDb();
		
	}

}
