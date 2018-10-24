package com.dgs.microservices.cryptocurrencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	/*
	  We need to create a query methood because we search by "from" and "to".
	  After providing a method definition like this, the Spring Data JPA will provide the
	  implementation for us to be able to search the table by using the "from" and the "to".
	*/
	
	ExchangeValue findByFromAndTo(String from, String to);
}
