package com.cathay.coindesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cathay.coindesk.model.CoinRequest;
import com.cathay.coindesk.model.CoinResponse;
import com.cathay.coindesk.service.CoindeskService;

@CrossOrigin(origins = "http://localhost:9100")
@RestController
@RequestMapping("/cathay/coin")
public class CoindeskController {
	
	@Autowired
	private CoindeskService coindeskService;
	
	/***
	 * 查詢幣別對應表資料API，並顯示其內容
	 */
	@PostMapping("/doCoinQuery")
	public ResponseEntity<CoinResponse> doCoinQuery(@RequestBody CoinRequest coinRequest) {
		CoinResponse response = this.coindeskService.doCoinQuery(coinRequest);
		return new ResponseEntity<CoinResponse>(response, HttpStatus.OK);
	}
	
	/***
	 * 新增幣別對應表資料API
	 */
	@PostMapping("/createCoin")
	public ResponseEntity<String> createCoin(@RequestBody CoinRequest coinRequest) {
		this.coindeskService.createCoin(coinRequest);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	/***
	 * 更新幣別對應表資料API，並顯示其內容
	 */
	@PostMapping("/updateCoin")
	public ResponseEntity<CoinResponse> updateCoin(@RequestBody CoinRequest coinRequest) {
		CoinResponse response = this.coindeskService.updateCoin(coinRequest);
		return new ResponseEntity<CoinResponse>(response, HttpStatus.OK);
	}
	
	/***
	 * 刪除幣別對應表資料API
	 */
	@PostMapping("/deleteCoin")
	public ResponseEntity<String> deleteCoin(@RequestBody CoinRequest coinRequest) {
		this.coindeskService.deleteCoin(coinRequest);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/***
	 * 呼叫 coindesk API，並顯示其內容
	 */
	@GetMapping("/callCoindeskAPI")
	public ResponseEntity<String> callCoindeskAPI() {
		String response = this.coindeskService.callCoindeskAPI();
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	/***
	 * 資料轉換的API，並顯示其內容
	 */
	@GetMapping("/convertData")
	public ResponseEntity<CoinResponse> convertData() {
		CoinResponse response = this.coindeskService.convertData();
		return new ResponseEntity<CoinResponse>(response, HttpStatus.OK);
	}
}
