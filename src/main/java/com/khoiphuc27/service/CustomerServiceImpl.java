package com.khoiphuc27.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khoiphuc27.dao.CustomerDAO;
import com.khoiphuc27.model.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDAO customerDAO;

	public void setCustomerDAO(CustomerDAO CustomerDAO) {
		this.customerDAO = CustomerDAO;
	}

	@Override
	@Transactional
	public void addCustomer(Customer p) {
		this.customerDAO.addCustomer(p);
	}

	@Override
	@Transactional
	public void updateCustomer(Customer p) {
		this.customerDAO.updateCustomer(p);
	}

	@Override
	@Transactional
	public List<Customer> listCustomers() {
		return this.customerDAO.listCustomers();
	}

	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		return this.customerDAO.getCustomerById(id);
	}

	@Override
	@Transactional
	public void removeCustomer(int id) {
		this.customerDAO.removeCustomer(id);
	}
	
	@Override
	@Transactional
	public List<Customer> searchCustomer(String name, String phone, String birthday, String email, String gender) {
		return this.customerDAO.searchCustomer(name, phone, birthday, email, gender);
	}

}
