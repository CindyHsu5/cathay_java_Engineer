package com.cathay.coindesk.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Coin")
@Data
public class Coin {

	@Id
	private String chartName;
	private String mandarinName;
	private Date time;
	private String disclaimer;
	
	public Coin() {}

	public Coin(String chartName, String mandarinName, String disclaimer) {
		this.chartName = chartName;
		this.mandarinName = mandarinName;
		this.disclaimer = disclaimer;
	}

}
