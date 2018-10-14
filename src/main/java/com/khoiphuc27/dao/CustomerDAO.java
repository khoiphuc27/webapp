package com.khoiphuc27.dao;

import java.util.List;

import com.khoiphuc27.dto.CustomerDTO;
import com.khoiphuc27.model.Customer;;

public interface CustomerDAO {
	
	public void addCustomer(Customer c);
	public void updateCustomer(Customer c);
	public void updateCustomer(int customerId, CustomerDTO customerDTO);
	public List<Customer> listCustomers();
	public Customer getCustomerById(int id);
	public void removeCustomer(int id);
	public List<Customer> searchCustomer(String name, String phone, String birthday, String email, String gender);
	public List<Customer> getCustomersPagination(int page, int itemsPerPage);
	public List<Customer> sortCustomers(String sortBy);
}
