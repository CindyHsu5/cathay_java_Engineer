# coins

## About
A simple database service for maintaining coins

## Installation
專案運行方式是使用本機`Spring Boot App`

## Used
- Java:8
- Spring Boot:2.7.7
- 資料庫:H2(OpenJPA / Spring Data JPA)

## API URLs
- 1. POST http://localhost:9100/cathay/coin/doCoinQuery 查詢幣別對應表資料API，並顯示其內容。

    Examples:
        
        Request 
	        {
	    		"chartName": "Bitcoin"
	        }
	        
        Response
	        {
			    "chartName": "Bitcoin",
			    "mandarinName": "比特幣",
			    "time": "2023/01/10 16:17:38",
			    "disclaimer": "This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
			    "bpi": {
			        "EUR": {
			            "coin": "Bitcoin",
			            "code": "EUR",
			            "symbol": "&euro;",
			            "rate": "16,496.4650",
			            "description": "Euro",
			            "rate_float": 16496.465
			        },
			        "GBP": {
			            "coin": "Bitcoin",
			            "code": "GBP",
			            "symbol": "&pound;",
			            "rate": "14,150.1522",
			            "description": "British Pound Sterling",
			            "rate_float": 14150.1522
			        },
			        "USD": {
			            "coin": "Bitcoin",
			            "code": "USD",
			            "symbol": "&#36;",
			            "rate": "16,934.2840",
			            "description": "United States Dollar",
			            "rate_float": 16934.284
			        }
			    }
			}
		       

- 2. POST http://localhost:9100/cathay/coin/createCoin 新增幣別對應表資料API。

    Examples:
        
        Request 
	        {
			    "disclaimer":"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
			    "chartName":"Bitcoin",
			    "mandarinName":"比特幣",
			    "bpi": {
				     "USD":{
				      "code":"USD","symbol":"&#36;","rate":"16,934.2840",
				      "description":"United States Dollar",
				      "rate_float":16934.284
				     },
				     "GBP":{
				      "code":"GBP","symbol":"&pound;","rate":"14,150.1522",
				      "description":"British Pound Sterling",
				      "rate_float":14150.1522
				     },
				     "EUR":{
				      "code":"EUR","symbol":"&euro;","rate":"16,496.4650",
				      "description":"Euro",
				      "rate_float":16496.465
					 }
				}
			}
	 
		      
- 3. POST http://localhost:9100/cathay/coin/updateCoin 更新幣別對應表資料API，並顯示其內容。
 
    Examples:
        
        Request 
	        {
			    "disclaimer":"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
			    "chartName":"Bitcoin",
			    "mandarinName":"特幣",
			    "bpi": {
				     "USD":{
				      "code":"USD","symbol":"&#36;","rate":"16,934.2840",
				      "description":"United States Dollar",
				      "rate_float":16934.284
				     },
				     "GBP":{
				      "code":"GBP","symbol":"&pound;","rate":"14,150.1522",
				      "description":"British Pound Sterling",
				      "rate_float":14150.1522
				     }
				}
			}
	        
        Response
	        {
			    "chartName": "Bitcoin",
			    "mandarinName": "特幣",
			    "time": "2023/01/10 12:22:15",
			    "disclaimer": "This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
			    "bpi": {
			        "USD": {
			            "coin": "Bitcoin",
			            "code": "USD",
			            "symbol": "&#36;",
			            "rate": "16,934.2840",
			            "description": "United States Dollar",
			            "rate_float": 16934.284
			        },
			        "GBP": {
			            "coin": "Bitcoin",
			            "code": "GBP",
			            "symbol": "&pound;",
			            "rate": "14,150.1522",
			            "description": "British Pound Sterling",
			            "rate_float": 14150.1522
			        }
			    }
			}
		       
- 4. POST http://localhost:9100/cathay/coin/deleteCoin 刪除幣別對應表資料API。
 
    Examples:
        
        Request 
	        {
	    		"chartName": "Bitcoin"
	        }
	 
		      
- 5. GET http://localhost:9100/cathay/coin/callCoindeskAPI 呼叫coindesk API，並顯示其內容。
 	
    Examples:
             
        Response
	        {
			    "time": {
			        "updated": "Jan 10, 2023 15:10:00 UTC",
			        "updatedISO": "2023-01-10T15:10:00+00:00",
			        "updateduk": "Jan 10, 2023 at 15:10 GMT"
			    },
			    "disclaimer": "This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
			    "chartName": "Bitcoin",
			    "bpi": {
			        "USD": {
			            "code": "USD",
			            "symbol": "&#36;",
			            "rate": "17,294.8653",
			            "description": "United States Dollar",
			            "rate_float": 17294.8653
			        },
			        "GBP": {
			            "code": "GBP",
			            "symbol": "&pound;",
			            "rate": "14,451.4511",
			            "description": "British Pound Sterling",
			            "rate_float": 14451.4511
			        },
			        "EUR": {
			            "code": "EUR",
			            "symbol": "&euro;",
			            "rate": "16,847.7238",
			            "description": "Euro",
			            "rate_float": 16847.7238
			        }
			    }
			}
			
- 6. GET http://localhost:9100/cathay/coin/convertData 資料轉換的API，並顯示其內容。
  	
    Examples:
             
        Response
	        {
			    "chartName": "Bitcoin",
			    "mandarinName": "比特幣",
			    "time": "2023/01/10 12:22:00",
			    "disclaimer": "This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
			    "bpi": {
			        "GBP": {
			            "coin": "Bitcoin",
			            "code": "GBP",
			            "symbol": "&pound;",
			            "rate": "14,150.1522",
			            "description": "British Pound Sterling",
			            "rate_float": 14150.1522
			        },
			        "USD": {
			            "coin": "Bitcoin",
			            "code": "USD",
			            "symbol": "&#36;",
			            "rate": "16,934.2840",
			            "description": "United States Dollar",
			            "rate_float": 16934.284
			        }
			    }
			}
			
