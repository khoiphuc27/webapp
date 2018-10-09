package com.khoiphuc27.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.khoiphuc27.model.Account;

@Repository
public class AccountDAOImpl implements AccountDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> listAccounts(String userName, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Criteria cr = session.createCriteria(Account.class);
		cr.add(Restrictions.eq("userName", userName));
		cr.add(Restrictions.eq("password", password));
		
		System.out.println(cr.list());
		
		List<Account> accountList = cr.list();
		return accountList;
	}
}
