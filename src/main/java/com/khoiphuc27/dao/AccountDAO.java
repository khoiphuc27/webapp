package com.khoiphuc27.dao;

import java.util.List;

import com.khoiphuc27.model.Account;

public interface AccountDAO {
	List<Account> listAccounts(String userName, String password);
}
