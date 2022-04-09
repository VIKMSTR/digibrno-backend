package org.hackujbrno.digibrno.servicebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServicebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicebackendApplication.class, args);
	}

}

