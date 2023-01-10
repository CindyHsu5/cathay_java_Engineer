package com.cathay.coindesk.model;

import java.util.Map;

public class CoinResponse {
	
	private String chartName;
	private String mandarinName;
	private String time;
	private String disclaimer;
	private Map<String, BPI> bpi;

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public String getMandarinName() {
		return mandarinName;
	}

	public void setMandarinName(String mandarinName) {
		this.mandarinName = mandarinName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public Map<String, BPI> getBpi() {
		return bpi;
	}

	public void setBpi(Map<String, BPI> bpi) {
		this.bpi = bpi;
	}

}
