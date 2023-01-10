package com.cathay.coindesk.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cathay.coindesk.model.CoinRequest;
import com.cathay.coindesk.model.CoinResponse;
import com.cathay.coindesk.service.CoindeskService;

@CrossOrigin(origins = "http://localhost:9100")
@RestController
@RequestMapping("/cathay/coin")
public class CoindeskController {
	
	@Autowired
	private CoindeskService coindeskService;
	
	@PostMapping("/createCoin")
	public ResponseEntity<String> createCoin(@RequestBody CoinRequest coinRequest) {
		this.coindeskService.createCoin(coinRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/doCoinQuery")
	public ResponseEntity<CoinResponse> doCoinQuery(@RequestBody CoinRequest coinRequest) {
		CoinResponse response = this.coindeskService.doCoinQuery(coinRequest);
		return new ResponseEntity<CoinResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/updateCoin")
	public ResponseEntity<CoinResponse> updateCoin(@RequestBody CoinRequest coinRequest) {
		CoinResponse response = this.coindeskService.updateCoin(coinRequest);
		return new ResponseEntity<CoinResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("/deleteCoin")
	public ResponseEntity<String> deleteCoin(@RequestBody CoinRequest coinRequest) {
		this.coindeskService.deleteCoin(coinRequest);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/callCoindeskAPI")
	public ResponseEntity<String> callCoindeskAPI() {
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/convertData")
	public ResponseEntity<CoinResponse> convertData() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);
		
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		CoinRequest coindeskApiModel = restTemplate.getForObject(url, CoinRequest.class);
		CoinResponse response = this.coindeskService.convertData(coindeskApiModel);
		return new ResponseEntity<CoinResponse>(response, HttpStatus.OK);
	}
}
