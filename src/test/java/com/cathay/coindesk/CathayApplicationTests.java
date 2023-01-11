package com.cathay.coindesk;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.cathay.coindesk.model.BPI;
import com.cathay.coindesk.model.CoinRequest;
import com.cathay.coindesk.model.CoinResponse;
import com.cathay.coindesk.repository.BPIRepository;
import com.cathay.coindesk.repository.CoinRepository;
import com.cathay.coindesk.service.CoindeskService;

@SpringBootTest
class CathayApplicationTests {

	@Autowired
	private CoindeskService coindeskService;

	@Autowired
	CoinRepository coinRepository;

	@Autowired
	BPIRepository bpiRepository;

	boolean hasDataFlag = false; // DB has Data = true

	/***
	 * 查詢幣別對應表資料API，並顯示其內容
	 */
	@Test
	void serviceDoCoinQueryTest() {
		// 存進DB
//		Coin coin = new Coin("Bitcoin", "比特幣",
//				"The data was produced from the CoinDesk Bitcoin Price Index (USD). 比特幣");
//		coinRepository.save(coin);
//		BPI element1 = new BPI("Bitcoin", "GBP", "&pound;", "14,150.1522", "British Pound Sterling", 14150.1522);
//		BPI element2 = new BPI("Bitcoin", "EUR", "&euro;", "16,496.4650", "Euro", 16496.465);
//		bpiRepository.save(element1);
//		bpiRepository.save(element2);

		CoinRequest req = new CoinRequest();
		req.setChartName("Bitcoin");

		CoinResponse actualRes = coindeskService.doCoinQuery(req);
		if(hasDataFlag) {
			Assert.assertEquals("Bitcoin", actualRes.getChartName());
		} else {
			Assert.assertEquals("not found!", actualRes.getChartName());
		}
	}

	/***
	 * 新增幣別對應表資料API
	 */
	@Test
	void serviceCreateCoinTest() {
		CoinRequest req = new CoinRequest();
		req.setDisclaimer(
				"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org");
		req.setChartName("Bitcoin");
		req.setMandarinName("比特幣");

		Map<String, BPI> bpiMap = new HashMap<String, BPI>();
		BPI element1 = new BPI("Bitcoin", "USD", "&#36;", "16,934.2840", "United States Dollar", 16934.284);
		BPI element2 = new BPI("Bitcoin", "GBP", "&pound;", "14,150.1522", "British Pound Sterling", 14150.1522);
		BPI element3 = new BPI("Bitcoin", "EUR", "&euro;", "16,496.4650", "Euro", 16496.465);
		bpiMap.put("USD", element1);
		bpiMap.put("GBP", element2);
		bpiMap.put("EUR", element3);
		req.setBpi(bpiMap);

		String actualRes = coindeskService.createCoin(req);
		Assert.assertEquals("Created Coin!", actualRes);
	}

	/***
	 * 更新幣別對應表資料API，並顯示其內容
	 */
	@Test
	void serviceUpdateCoinTest() {
		CoinRequest req = new CoinRequest();
		req.setDisclaimer("Non-USD currency data converted using hourly conversion rate from openexchangerates.org");
		req.setChartName("Bitcoin");
		req.setMandarinName("特比幣");

		Map<String, BPI> bpiMap = new HashMap<String, BPI>();
		BPI element1 = new BPI("Bitcoin", "EUR", "&euro;", "16,934.2840", "Euroooooooooo", 16934.284);
		BPI element2 = new BPI("Bitcoin", "GBP", "&pound;", "14,150.1522", "British Pound Sterling", 14150.1522);
		bpiMap.put("EUR", element1);
		bpiMap.put("GBP", element2);
		req.setBpi(bpiMap);

		CoinResponse actualRes = coindeskService.updateCoin(req);

		Assert.assertEquals("Bitcoin", actualRes.getChartName());
	}

	/***
	 * 刪除幣別對應表資料API
	 */
	@ExtendWith(MockitoExtension.class)
	void serviceDeleteCoinTest(@RequestBody CoinRequest coinRequest) {
		CoinRequest req = new CoinRequest();
		req.setChartName("Bitcoin");
		String actualRes = coindeskService.deleteCoin(req);
		
		if(hasDataFlag) {
			Assert.assertEquals("Deleted Coin", actualRes);
		} else {
			Assert.assertEquals("Exception!", actualRes);
		}
	}

	/***
	 * 呼叫 coindesk API，並顯示其內容
	 */
	@Test
	void serviceCallCoindeskApiTest() {
		
		String actualRes = coindeskService.callCoindeskAPI();
		
		String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
		RestTemplate restTemplate = new RestTemplate();
		String expectedRes = restTemplate.getForObject(url, String.class);
		
		Assert.assertEquals(expectedRes, actualRes);
	}

	/***
	 * 資料轉換的API，並顯示其內容
	 */
	@Test
	void serviceConvertDataTest() {
		CoinResponse actualRes = coindeskService.convertData();
		
		if(hasDataFlag) {
			Assert.assertEquals("Bitcoin", actualRes.getChartName());
		} else {
			Assert.assertEquals("not found!", actualRes.getChartName());
		}
	}
}
