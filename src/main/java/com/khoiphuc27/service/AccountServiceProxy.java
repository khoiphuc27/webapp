//package com.khoiphuc27.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.khoiphuc27.dao.AccountDAO;
//import com.khoiphuc27.model.Account;
//import com.khoiphuc27.model.Customer;
//
//
//public class AccountServiceProxy implements UserDetailsService{
//	
//	@Autowired
//	private AccountDAO accountDAO;
//
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		Account account = accountDAO.loadAccountByUsername(username);
//		if (account == null) {
//			return null;
//		}
//		
//		boolean enabled = true;
//		boolean accountNonExpired = true;
//		boolean credentialsNonExpired = true;
//		boolean accountNonLocked = true;
//
//		return new User(username, account.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
//				accountNonLocked, account.getAuthorities());
//		
//	}
//	
//}
