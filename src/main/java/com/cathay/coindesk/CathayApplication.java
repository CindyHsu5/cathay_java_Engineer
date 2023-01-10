package com.cathay.coindesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.cathay.coindesk.repository.CoinRepository;


@SpringBootApplication
public class CathayApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(CathayApplication.class, args);
		CoinRepository coinDetailRepository = configurableApplicationContext.getBean(CoinRepository.class);
//		Coin coin = new();
	}

}
 