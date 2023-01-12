package com.cathay.coindesk.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@IdClass(BPIPk.class)
@Table(name = "BPI")
@Data
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
	
}
