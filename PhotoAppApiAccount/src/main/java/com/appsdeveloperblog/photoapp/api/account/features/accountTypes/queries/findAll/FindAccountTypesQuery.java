package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.queries.findAll;

import lombok.Data;

@Data
public class FindAccountTypesQuery {
	private String accountTypeId;
	private String accountName;
	private double price;
	private String description;
}
