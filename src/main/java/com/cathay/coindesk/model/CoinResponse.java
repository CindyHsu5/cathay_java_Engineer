package com.cathay.coindesk.model;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class CoinResponse {
	
	private String chartName;
	private String mandarinName;
	private String time;
	private String disclaimer;
	private Map<String, BPI> bpi;

}
