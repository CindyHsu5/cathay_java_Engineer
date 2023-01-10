package com.cathay.coindesk.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(BPIPk.class)
@Table(name = "BPI")
public class BPI {
	
	@Id
	private String coin;
	@Id
	private String code; 
	private String symbol;
	private String rate;
	private String description;
	private double rate_float;
	
	public BPI() {}
	
	public BPI(String coin, String code, String symbol, String rate, String description, double rate_float) {
		this.coin = coin;
		this.code = code;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.setRate_float(rate_float);
	}
	
	public String getCoin() {
		return coin;
	}
	
	public void setCoin(String coin) {
		this.coin = coin;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public String getRate() {
		return rate;
	}
	
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public double getRate_float() {
		return rate_float;
	}

	public void setRate_float(double rate_float) {
		this.rate_float = rate_float;
	}
}
