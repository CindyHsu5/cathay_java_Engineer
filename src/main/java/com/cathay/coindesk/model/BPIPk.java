package com.cathay.coindesk.model;

import java.io.Serializable;

public class BPIPk implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -397465953460515834L;

	private String coin;
	
	private String code;

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

}
