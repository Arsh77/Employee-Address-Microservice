package com.learning.employee.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeAppConfiguration {
    @Bean
    ModelMapper getModelMapper() {
		return new ModelMapper();
	}
    
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(RestTemplateBuilder builder) {
     
    	return builder.build();
    }
    
    @Bean
    @LoadBalanced
    WebClient getwebClient() {
    	return WebClient.builder().build();
    }
}
