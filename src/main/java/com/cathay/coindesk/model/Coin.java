package com.cathay.coindesk.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Coin")
public class Coin {

	@Id
	private String chartName;
	private String mandarinName;
	private Date time;
	private String disclaimer;
	
	public Coin() {
	}

	public Coin(String chartName, String mandarinName, String disclaimer) {
		this.chartName = chartName;
		this.mandarinName = mandarinName;
		this.disclaimer = disclaimer;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
}
