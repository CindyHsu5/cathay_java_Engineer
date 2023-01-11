package com.cathay.coindesk.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cathay.coindesk.model.BPI;
import com.cathay.coindesk.model.Coin;
import com.cathay.coindesk.model.CoinRequest;
import com.cathay.coindesk.model.CoinResponse;
import com.cathay.coindesk.repository.BPIRepository;
import com.cathay.coindesk.repository.CoinRepository;

@Service
public class CoindeskService {

	@Autowired
	CoinRepository coinRepository;

	@Autowired
	BPIRepository bpiRepository;
	
	/***
	 * 查詢幣別對應表資料API，並顯示其內容
	 */
	public CoinResponse doCoinQuery(CoinRequest request) {

		CoinResponse response = new CoinResponse();
		
		Optional<Coin> coinOptional = coinRepository.findById(request.getChartName());
		if(!coinOptional.isPresent()) {
			response.setChartName("not found!");
			return response;
		}
		Coin coin = coinOptional.get();
	
		ArrayList<BPI> bpiList = bpiRepository.findByCoin(coin.getChartName());
		if(bpiList.size() == 0) {
			response.setChartName("not found!");
			return response;
		}
		
		Map<String, BPI> bpiMap = new HashMap<String, BPI>();
		for (BPI element : bpiList) {
			bpiMap.put(element.getCode(), element);
		}
		
		response.setChartName(coin.getChartName());
		response.setMandarinName(coin.getMandarinName());
		response.setTime(this.getDateFormat(coin.getTime()));
		response.setDisclaimer(coin.getDisclaimer());
		response.setBpi(bpiMap);
		return response;
	}
	
	/***
	 * 新增幣別對應表資料API
	 */
	public String createCoin(CoinRequest request) {
		Coin coin = new Coin(request.getChartName(), request.getMandarinName(), request.getDisclaimer());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String dateString = formatter.format(new Date());
		
		try {
			Date date = formatter.parse(dateString);
			coin.setTime(date);
			coinRepository.save(coin);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Map<String, BPI> bpiMap = request.getBpi();
		for (Entry<String, BPI> entry : bpiMap.entrySet()) {
			BPI bpi = entry.getValue();
			bpi.setCoin(request.getChartName());
			bpiRepository.save(bpi);
		}
		return "Created Coin!";
	}
	
	/***
	 * 更新幣別對應表資料API，並顯示其內容
	 */
	public CoinResponse updateCoin(CoinRequest request) {
		this.deleteCoin(request);

		Coin coin = new Coin(request.getChartName(), request.getMandarinName(), request.getDisclaimer());
		coin.setTime(new Date());
		coinRepository.save(coin);

		Map<String, BPI> bpiMap = request.getBpi();
		for (Entry<String, BPI> entry : bpiMap.entrySet()) {
			BPI bpi = entry.getValue();
			bpi.setCoin(request.getChartName());
			bpiRepository.save(bpi);
		}

		CoinResponse response = new CoinResponse();
		response.setChartName(coin.getChartName());
		response.setMandarinName(coin.getMandarinName());
		response.setTime(this.getDateFormat(coin.getTime()));
		response.setDisclaimer(coin.getDisclaimer());
		response.setBpi(bpiMap);
		return response;
	}
	
	/***
	 * 刪除幣別對應表資料API
	 */
	public String deleteCoin(CoinRequest request) {
		String stringReturnString= "Deleted Coin!";
		try {
			bpiRepository.deleteByCoin(request.getChartName());
			coinRepository.deleteById(request.getChartName());
		} catch (Exception e) {
			stringReturnString= "Exception!";
		}
		return stringReturnString;
	}

	/***
	 * 呼叫 coindesk API，並顯示其內容
	 */
	public String callCoindeskAPI() {
		// call coindesk API
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(url, String.class);
		return response;
	}
	
	/***
	 * 資料轉換的API，並顯示其內容
	 */
	public CoinResponse convertData() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);
		
		// call coindesk API
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		CoinRequest coinRequest = restTemplate.getForObject(url, CoinRequest.class);
		CoinResponse response = new CoinResponse();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String dateString = this.getDateFormat(new Date(coinRequest.getTime().getUpdated()));
		
		Optional<Coin> coinOptional = coinRepository.findById(coinRequest.getChartName());
		if(!coinOptional.isPresent()) {
			response.setChartName("not found!");
			return response;
		}
		Coin coin = coinOptional.get();
		
		ArrayList<BPI> bpiList = bpiRepository.findByCoin(coin.getChartName());
		if(bpiList.size() == 0) {
			response.setChartName("not found!");
			return response;
		}
		
		Map<String, BPI> bpiMap = new HashMap<String, BPI>();
		for (BPI element : bpiList) {
			bpiMap.put(element.getCode(), element);
		}
		
		response.setChartName(coin.getChartName());
		response.setMandarinName(coin.getMandarinName());
		response.setTime(dateString);
		response.setDisclaimer(coin.getDisclaimer());
		response.setBpi(bpiMap);
		return response;
	}

	/***
	 * Date Format
	 */
	private String getDateFormat(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // 1990/01/01 00:00:00
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.format(date);
	}

}
