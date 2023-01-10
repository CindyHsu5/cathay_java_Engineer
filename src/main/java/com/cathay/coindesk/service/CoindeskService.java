package com.cathay.coindesk.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public void createCoin(CoinRequest request) {
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
	}

	public CoinResponse doCoinQuery(CoinRequest request) {
		Coin coin = coinRepository.findById(request.getChartName()).get();

		ArrayList<BPI> bpilist = bpiRepository.findByCoin(coin.getChartName());
		Map<String, BPI> bpiMap = new HashMap<String, BPI>();
		for (BPI element : bpilist) {
			bpiMap.put(element.getCode(), element);
		}

		CoinResponse response = new CoinResponse();
		response.setChartName(coin.getChartName());
		response.setMandarinName(coin.getMandarinName());
		response.setTime(this.getDateFormat(coin.getTime()));
		response.setDisclaimer(coin.getDisclaimer());
		response.setBpi(bpiMap);
		return response;
	}

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

	public void deleteCoin(CoinRequest request) {
		bpiRepository.deleteByCoin(request.getChartName());
		coinRepository.deleteById(request.getChartName());
	}

	public CoinResponse convertData(CoinRequest coinRequest) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String dateString = this.getDateFormat(new Date(coinRequest.getTime().getUpdated()));
		
		Coin coin = coinRepository.findById(coinRequest.getChartName()).get();

		ArrayList<BPI> bpilist = bpiRepository.findByCoin(coin.getChartName());
		Map<String, BPI> bpiMap = new HashMap<String, BPI>();
		for (BPI element : bpilist) {
			bpiMap.put(element.getCode(), element);
		}
		
		CoinResponse response = new CoinResponse();
		response.setChartName(coin.getChartName());
		response.setMandarinName(coin.getMandarinName());
		response.setTime(dateString);
		response.setDisclaimer(coin.getDisclaimer());
		response.setBpi(bpiMap);
		return response;
	}

	private String getDateFormat(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // 1990/01/01 00:00:00
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		return formatter.format(date);
	}

}
