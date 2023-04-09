package com.learning.address.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressAppConfiguration {

    @Bean
    ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
