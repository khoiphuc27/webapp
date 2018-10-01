package com.khoiphuc27.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.khoiphuc27.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addCustomer(Customer c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);
	}

	@Override
	public void updateCustomer(Customer c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> listCustomers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Customer> customersList = session.createQuery("from Customer").list();
		return customersList;
	}

	@Override
	public Customer getCustomerById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Customer c = (Customer) session.load(Customer.class, new Integer(id));
		return c;
	}

	@Override
	public void removeCustomer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer c = (Customer) session.load(Customer.class, new Integer(id));
		if(null != c){
			session.delete(c);
		}
	}

	@Override
	public List<Customer> searchCustomer(String name, String phone, String birthday, String email, String gender) {
		Session session = this.sessionFactory.getCurrentSession();
		
		String hqlQuery = "FROM Customer WHERE name = :name";
		if (!phone.isEmpty())
			hqlQuery += " AND phone = :phone";
		if (!birthday.isEmpty())
			hqlQuery += " AND dateOfBirth = :birthday";
		if (!email.isEmpty())
			hqlQuery += " AND email = :email";
		if (!gender.isEmpty())
			hqlQuery += " AND gender = :gender";
		
		Query query = session.createQuery(hqlQuery);
		System.out.println("Debug: " + name + " " + phone + " " + email + " " + birthday + " " + gender);
		
		if (!name.isEmpty())
			query.setParameter("name", name);
		if (!phone.isEmpty())
			query.setParameter("phone", phone);
		if (!birthday.isEmpty())
			query.setParameter("birthday", birthday);
		if (!email.isEmpty())
			query.setParameter("email", email);
		if (!gender.isEmpty())
			query.setParameter("gender", (gender=="Male")? true : false);
		
		List<Customer> customersList = query.list();
		return customersList;
	}
}
