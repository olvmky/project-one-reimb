package com.example.projectOne;

import com.example.projectOne.DAO.UserRepository;
import com.example.projectOne.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProjectOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOneApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

//	@Bean
//	public WebClient.Builder getWebClient() {
//		return WebClient.builder();
//	}


}
