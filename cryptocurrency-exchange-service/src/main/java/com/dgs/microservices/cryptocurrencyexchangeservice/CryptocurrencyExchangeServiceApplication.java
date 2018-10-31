package com.dgs.microservices.cryptocurrencyexchangeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableDiscoveryClient
public class CryptocurrencyExchangeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyExchangeServiceApplication.class, args);
	}
	
	// We need to create a Sample for adding Spring Cloud Sleuth
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
