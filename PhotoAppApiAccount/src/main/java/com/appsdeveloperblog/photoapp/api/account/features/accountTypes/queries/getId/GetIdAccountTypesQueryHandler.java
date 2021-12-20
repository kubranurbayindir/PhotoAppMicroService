package com.appsdeveloperblog.photoapp.api.account.features.accountTypes.queries.getId;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.photoapp.api.account.domain.AccountType;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.queries.findAll.FindAccountTypesQuery;
import com.appsdeveloperblog.photoapp.api.account.features.accountTypes.restModels.AccountTypeListRestModel;
import com.appsdeveloperblog.photoapp.api.account.persistance.AccountTypeRepository;

@Component
public class GetIdAccountTypesQueryHandler {
	
	private AccountTypeRepository accountTypeRepository;

	@Autowired
	public GetIdAccountTypesQueryHandler(AccountTypeRepository accountTypeRepository) {
		this.accountTypeRepository = accountTypeRepository;
	}
	
	@QueryHandler
	public AccountTypeListRestModel getIdAccountType(GetIdAccountTypesQuery getIdAccountTypesQuery) {
		AccountType accountType = this.accountTypeRepository.getById(getIdAccountTypesQuery.getAccountTypeId());
		AccountTypeListRestModel accountTypeListRestModel = new AccountTypeListRestModel();
		BeanUtils.copyProperties(accountType, accountTypeListRestModel);
		return accountTypeListRestModel;
	}
	
}
