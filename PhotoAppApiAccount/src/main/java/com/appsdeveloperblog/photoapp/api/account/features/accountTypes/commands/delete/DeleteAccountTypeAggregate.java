package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.delete;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create.AccountTypeCreatedEvent;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.commands.create.CreateAccountTypeCommand;

@Aggregate
public class DeleteAccountTypeAggregate {
	
	private String accountTypeId;
	
	@AggregateIdentifier
	private String identifier = UUID.randomUUID().toString();
	
	public DeleteAccountTypeAggregate() {
		
	}
	
	@CommandHandler
	public DeleteAccountTypeAggregate(DeleteAccountTypeCommand deleteAccountTypeCommand) {
		AccountTypeDeletedEvent accountTypeDeletedEvent = new AccountTypeDeletedEvent();
		BeanUtils.copyProperties(deleteAccountTypeCommand, accountTypeDeletedEvent);
		AggregateLifecycle.apply(accountTypeDeletedEvent);
	}
	
	@EventSourcingHandler //event source gördüğünde commandhandle i handlerı yakalıyor
	public void on(AccountTypeDeletedEvent accountTypeDeletedEvent) {//beande modelde yapabilirsin, fakat genelde 2 3 data gönderildiği için yukarıda dtaları koyarız istediğimizi mapleriz.
		this.accountTypeId = accountTypeDeletedEvent.getAccountTypeId();
		
	}

}
