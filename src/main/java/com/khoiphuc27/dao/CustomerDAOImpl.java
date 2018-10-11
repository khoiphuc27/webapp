package com.khoiphuc27.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		List<Customer> customersList = session.createQuery("FROM Customer").list();
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
		
		Criteria cr = session.createCriteria(Customer.class);
		
		if (!name.isEmpty())
			cr.add(Restrictions.like("name", "%" + name + "%"));
		if (!phone.isEmpty())
			cr.add(Restrictions.eq("phone", phone));
		if (!birthday.isEmpty())
			cr.add(Restrictions.eq("dateOfBirth", birthday));
		if (!email.isEmpty())
			cr.add(Restrictions.eq("email", email));
		if (!gender.isEmpty()) {
			if (gender.equals("Male"))
				cr.add(Restrictions.eq("gender", true));
			else //gender == Female
				cr.add(Restrictions.eq("gender", false));
		}
		
		@SuppressWarnings("unchecked")
		List<Customer> listCustomers = cr.list();
		
		return listCustomers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomersPagination(int page, int itemsPerPage) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Customer.class);
		
		cr.setMaxResults(itemsPerPage);
		cr.setFirstResult((page - 1) * itemsPerPage);
		
		List<Customer> listCustomers = cr.list();
		
		return listCustomers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> sortCustomers(String sortBy) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Customer.class);
		
		if (sortBy.equals("name"))
			cr.addOrder(Order.asc("name"));
		else if (sortBy.equals("birthday"))
			cr.addOrder(Order.asc("dateOfBirth"));
		else if (sortBy.equals("phone"))
			cr.addOrder(Order.asc("phone"));
		else if (sortBy.equals("email"))
			cr.addOrder(Order.asc("email"));
		
		List<Customer> listCustomers = cr.list();
		return listCustomers;
	}
}
