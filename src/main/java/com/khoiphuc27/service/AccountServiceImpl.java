package com.khoiphuc27.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khoiphuc27.dao.AccountDAO;
import com.khoiphuc27.dao.CustomerDAO;
import com.khoiphuc27.model.Account;
import com.khoiphuc27.model.Customer;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	private AccountDAO accountDAO;
	
	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
	
	@Override
	public List<Account> listAccounts(String userName, String password) {
		return this.accountDAO.listAccounts(userName, password);
	}
	
}
