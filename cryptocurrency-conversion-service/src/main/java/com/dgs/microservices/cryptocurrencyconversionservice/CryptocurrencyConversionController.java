package com.dgs.microservices.cryptocurrencyconversionservice;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptocurrencyConversionController {
	
	@GetMapping("cryptocurrency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CryptocurrencyConversionBean convertCurrency(@PathVariable String from, 
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		return new CryptocurrencyConversionBean(1L, from, to, BigDecimal.ONE, quantity, quantity, 0);
	}
}
