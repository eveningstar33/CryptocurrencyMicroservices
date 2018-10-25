package com.dgs.microservices.cryptocurrencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.dgs.microservices.cryptocurrencyconversionservice")
public class CryptocurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptocurrencyConversionServiceApplication.class, args);
	}
}
