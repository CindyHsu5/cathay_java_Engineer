package com.cathay.coindesk.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class BPIPk implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -397465953460515834L;

	private String coin;
	
	private String code;

}
