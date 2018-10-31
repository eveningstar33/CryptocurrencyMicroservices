package com.dgs.microservices.cryptocurrencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CryptocurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private CryptocurrencyExchangeServiceProxy proxy;
	
	@GetMapping("cryptocurrency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CryptocurrencyConversionBean convertCryptocurrency(@PathVariable String from, 
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		/*
		  We need to map the response which is coming back to an entity and we will send a GET request.
		  We are calling CryptocurrencyExchangeService and we are setting the variables to it,
		  we are getting the response back taking it into the bean and we are creating a new response bean 
		  by using the content which is present in the response.
		*/
		
		ResponseEntity<CryptocurrencyConversionBean> responseEntity = 
				new RestTemplate().getForEntity("http://localhost:8000/cryptocurrency-exchange/from/{from}/to/{to}", 
				CryptocurrencyConversionBean.class, uriVariables);
		
		CryptocurrencyConversionBean response = responseEntity.getBody(); 
		
		return new CryptocurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), 
				quantity, quantity.multiply(response.getConversionMultiple()), response.getPort()); 
	}
	
	/*
	  We have Feign enabled on this project and we need to use Feign to invoke the service.  
	  Just like we use a repository to talk to JPA, we need to create a Feign proxy to be able
	  to talk to externally microservice. 
	*/
	
	@GetMapping("cryptocurrency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CryptocurrencyConversionBean convertCryptocurrencyFeign(@PathVariable String from, 
			@PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CryptocurrencyConversionBean response = proxy.retrieveExchangeValue(from, to); 
		
		logger.info("{}", response); 
		
		return new CryptocurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), 
				quantity, quantity.multiply(response.getConversionMultiple()), response.getPort()); 
	}
}
