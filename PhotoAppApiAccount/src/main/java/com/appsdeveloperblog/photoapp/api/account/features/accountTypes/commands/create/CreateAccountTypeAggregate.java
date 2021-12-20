package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CreateAccountTypeAggregate {
	
	@AggregateIdentifier
	private String accountTypeId;
	private String accountName;
	private double price;
	private String description;
	
	public CreateAccountTypeAggregate() {
		
	}	
	
	@CommandHandler
	public CreateAccountTypeAggregate(CreateAccountTypeCommand createAccountTypeCommand) {
		//validation
		if(createAccountTypeCommand.getPrice()<0) {
			throw new IllegalArgumentException("Fiyat sıfırdan küçük olamaz.");
		}
		//business
		
		//event fire
		AccountTypeCreatedEvent accountTypeCreatedEvent = new AccountTypeCreatedEvent();
		//model mapper, builder ile command nesnesini mapleywbliriez. last thing yo know: bean utils
		BeanUtils.copyProperties(createAccountTypeCommand, accountTypeCreatedEvent);//source,target
		
		AggregateLifecycle.apply(accountTypeCreatedEvent);
	}
	
	@EventSourcingHandler //event source gördüğünde commandhandle i handlerı yakalıyor
	public void on(AccountTypeCreatedEvent accountTypeCreatedEvent) {//beande modelde yapabilirsin, fakat genelde 2 3 data gönderildiği için yukarıda dtaları koyarız istediğimizi mapleriz.
		this.accountTypeId = accountTypeCreatedEvent.getAccountTypeId();
		this.accountName = accountTypeCreatedEvent.getAccountName();
		this.description = accountTypeCreatedEvent.getDescription();
		this.price = accountTypeCreatedEvent.getPrice();
		
	}

}
