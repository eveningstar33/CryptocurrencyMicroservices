package com.dgs.microservices.cryptocurrencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//It will use Feign to talk to an external microservice

@FeignClient(name="cryptocurrency-exchange-service", url="localhost:8000")  
public interface CryptocurrencyExchangeServiceProxy {

	/*
	  We need to define a method to talk to the CryptocurrencyExchangeService.
	  We take the method definition from CryptocurrencyExchangeController and make 
	  a simple modification in terms of the return values. 
	  When you are using Feign you need to specify the exact path variables in here:
	  @PathVariable("from") String from, @PathVariable("to") String to
	*/
	
	@GetMapping("/cryptocurrency-exchange/from/{from}/to/{to}")
	public CryptocurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
