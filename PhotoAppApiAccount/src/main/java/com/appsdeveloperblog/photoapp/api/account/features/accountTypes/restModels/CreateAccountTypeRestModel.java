package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.restModels;

import lombok.Data;

//import java.math.BigDecimal;

@Data
public class CreateAccountTypeRestModel {

	private String accountName;//free, business, family
	//private BigDecimal price;
	private double price;
	private String description;
	
	
}
