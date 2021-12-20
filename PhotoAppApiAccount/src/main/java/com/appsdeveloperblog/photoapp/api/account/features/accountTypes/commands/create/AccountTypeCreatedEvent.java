package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AccountTypeCreatedEvent {

	private String accountTypeId;
	private String accountName;
	private double price;
	private String description;
}
