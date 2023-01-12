package com.cathay.coindesk.model;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CoinRequest {

	private String chartName;
	private String mandarinName;
	private String disclaimer;
	private Map<String, BPI> bpi;
	private Time time;
	
	@Data
	@EqualsAndHashCode
	public class Time {
		private String updated;
		private String updatedISO;
		private String updateduk;
	}

}
