package com.microservices.currencyexchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	@Autowired
	private ExchangeCurrencyRepository repo;
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retreiveCurrencyValue(@PathVariable String from, @PathVariable String to) {
		
		//ExchangeValue exchange = new ExchangeValue(100L, "USD", "INR", BigDecimal.valueOf(75));
		
		ExchangeValue exchange = repo.findByFromAndTo(from, to);
		int port = Integer.parseInt(environment.getProperty("local.server.port"));
		exchange.setPort(port);
		
		return exchange;
	}
	
}
