package com.khoiphuc27.service;

import java.util.List;

import com.khoiphuc27.model.Account;

public interface AccountService {

	List<Account> listAccounts(String userName, String password);

}
