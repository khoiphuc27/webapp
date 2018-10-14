package com.khoiphuc27.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khoiphuc27.dao.CustomerDAO;
import com.khoiphuc27.dto.CustomerDTO;
import com.khoiphuc27.model.Customer;
import com.khoiphuc27.model.titleEnum;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDAO customerDAO;

	public void setCustomerDAO(CustomerDAO CustomerDAO) {
		this.customerDAO = CustomerDAO;
	}

	@Override
	public void addCustomer(Customer p) {
		this.customerDAO.addCustomer(p);
	}

	@Override
	public void updateCustomer(Customer p) {
		this.customerDAO.updateCustomer(p);
	}

	@Override
	public List<Customer> listCustomers() {
		return this.customerDAO.listCustomers();
	}

	@Override
	public CustomerDTO getCustomerDTOById(int id) {
		Customer customerModel = this.customerDAO.getCustomerById(id);
		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setId(customerModel.getId());
		customerDTO.setName(customerModel.getName());
		customerDTO.setPhone(customerModel.getPhone());
		customerDTO.setBirthday(customerModel.getDateOfBirth());
		customerDTO.setEmail(customerModel.getEmail());
		customerDTO.setAddress(customerModel.getAddressLine());
		customerDTO.setGender((customerModel.isGender()));
		customerDTO.setTitle(customerModel.getTitle().equals("MR") ? titleEnum.MR : titleEnum.MRS);
		
		//Debug
		System.out.println(customerDTO.isGender());
		
		return customerDTO;
	}

	@Override
	public void removeCustomer(int id) {
		this.customerDAO.removeCustomer(id);
	}
	
	@Override
	public List<Customer> searchCustomer(String name, String phone, String birthday, String email, String gender) {
		return this.customerDAO.searchCustomer(name, phone, birthday, email, gender);
	}

	@Override
	public List<Customer> getCustomersPagination(int page, int itemsPerPage) {
		return this.customerDAO.getCustomersPagination(page, itemsPerPage);
	}

	@Override
	public List<Customer> sortCustomers(String sortBy) {
		return this.customerDAO.sortCustomers(sortBy);
	}

	@Override
	public Customer getCustomerById(int id) {
		return this.customerDAO.getCustomerById(id);
	}

	@Override
	public void updateCustomer(int customerId, CustomerDTO customerDTO) {
		this.customerDAO.updateCustomer(customerId, customerDTO);
		
	}

}
