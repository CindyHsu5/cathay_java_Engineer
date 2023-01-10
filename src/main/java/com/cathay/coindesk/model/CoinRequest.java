package com.cathay.coindesk.model;

import java.util.Map;

public class CoinRequest {

	private String chartName;
	private String mandarinName;
	private String disclaimer;
	private Map<String, BPI> bpi;
	private Time time;

	public class Time {
		private String updated;
		private String updatedISO;
		private String updateduk;
		
		public String getUpdated() {
			return updated;
		}
		public void setUpdated(String updated) {
			this.updated = updated;
		}
		public String getUpdatedISO() {
			return updatedISO;
		}
		public void setUpdatedISO(String updatedISO) {
			this.updatedISO = updatedISO;
		}
		public String getUpdateduk() {
			return updateduk;
		}
		public void setUpdateduk(String updateduk) {
			this.updateduk = updateduk;
		}
	}

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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
