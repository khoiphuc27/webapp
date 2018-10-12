package com.khoiphuc27.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.khoiphuc27.dao.CustomerDAO;
import com.khoiphuc27.dto.CustomerDTO;
import com.khoiphuc27.model.Account;
import com.khoiphuc27.model.Customer;
import com.khoiphuc27.model.titleEnum;
import com.khoiphuc27.service.AccountService;
import com.khoiphuc27.service.CustomerService;
import com.khoiphuc27.utils.ExcelWriter;
import com.khoiphuc27.view.ExcelReportView;

@Controller
public class MainController {
	@Autowired(required=true)
	private AccountService accountService;
	
	@Autowired(required=true)
	private CustomerService customerService;
	
//	@Autowired
//	@Qualifier("customerFormValidator")
//	private Validator customerFormValidator;
//	
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(customerFormValidator);
//	}
//	
	@RequestMapping("/")
	public String home(Model model) {
		return "redirect:/login";
	}	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("account", new Account());
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processingLogin(@ModelAttribute("account") Account account, Model model) {
		List<Account> result = accountService.listAccounts(account.getUserName(), account.getPassword());
		
		if (!validateInput(account.getUserName(), account.getPassword())) {
			model.addAttribute("errorMsg", "Input validation failed" );
			return "login";
		}
		
		if (result.size() != 0) {
			return "redirect:/customers";
		} else {
			model.addAttribute("errorMsg", "Login failed" );
			return "login";
		}
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String customers (@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "sort", required=false) String sort, Model model) {
		final int ITEMS_PER_PAGE = 5;
//		PAGINATION
//		Use pagination
		int numOfCustomers = customerService.listCustomers().size();
		int numOfPages;
		
		if (numOfCustomers % ITEMS_PER_PAGE == 0)
			numOfPages = numOfCustomers / ITEMS_PER_PAGE;
		else
			numOfPages = numOfCustomers / ITEMS_PER_PAGE + 1;
		
		model.addAttribute("numOfPages", numOfPages);
		model.addAttribute("page", page);
		
		List<Integer> listPages = new ArrayList<Integer>();
		for (int i = 0; i < numOfPages; i++)
			listPages.add(i + 1);
		
		model.addAttribute("listPages", listPages);
		model.addAttribute("gender", "Male");
		
//		SORT
		if (sort != null) {
			model.addAttribute("listCustomers", customerService.sortCustomers(sort));
		}
		else
			model.addAttribute("listCustomers", customerService.getCustomersPagination(page, ITEMS_PER_PAGE));
		
		model.addAttribute("customer", new Customer());
		return "customers";
	}

	private boolean validateInput(String userName, String password) {
		if (userName.isEmpty() || !userName.equals(userName.toLowerCase()))
			return false;
		if (password.length() < 7 || !password.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]+$"))
			return false;
				
		return true;
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public String customersAction(HttpServletRequest request, @ModelAttribute("customer") @Valid Customer customer, BindingResult result, @RequestParam(value="selectedIds", required=false) int ids[], RedirectAttributes redirectAttributes, Model model) throws InvalidFormatException, IOException {		
		
		String searchBtn = request.getParameter("searchBtn");
		String resetBtn = request.getParameter("resetBtn");
		String newBtn = request.getParameter("newBtn");
		String updateBtn = request.getParameter("updateBtn");
		String deleteBtn = request.getParameter("deleteBtn");
		String exportBtn = request.getParameter("exportBtn");
		
		if (searchBtn != null ) {
			if (result.hasErrors())
				return "customers";
			
			String name = customer.getName();
			String phone = customer.getPhone();
			String birthday = customer.getDateOfBirth();
			String email = customer.getEmail();
			String gender = (customer.isGender() == true) ? "Male" : "Female";
			
			List<Customer> listCustomers = customerService.searchCustomer(name, phone, birthday, email, gender);
			
			model.addAttribute("listCustomers", listCustomers);
			return "customers";
		}
		else if (resetBtn != null) {
			return "redirect:/customers";
		}
		else if (newBtn != null)
			return "redirect:/customer";
		else if (updateBtn != null) {
			if (ids != null && ids.length > 0) {
				CustomerDTO selectedForUpdateCustomer = customerService.getCustomerDTOById(ids[0]);
				redirectAttributes.addFlashAttribute("customer", selectedForUpdateCustomer);
				//Debug
				System.out.println("Selected Customer IDs:");
				for (int i : ids) System.out.println(i);
			}
			return "redirect:/customer";
		}
		else if (exportBtn != null) {
			List<Customer> listCustomers = this.customerService.listCustomers();
			ExcelWriter.exportExcelCustomerList("List_Customers", listCustomers);
			return "customers";
		}
		else return "customers";
	}
	
	@RequestMapping(value="/customer", method=RequestMethod.GET)
	public String customer(Model model) {
		if (!model.containsAttribute("customer"))
			model.addAttribute("customer", new CustomerDTO());
		model.addAttribute("titleItems", titleEnum.values());
		return "customer";
	}
	
	@RequestMapping(value="/customer", method=RequestMethod.POST)
	public String customerAction(HttpServletRequest request, @ModelAttribute("customer") @Valid CustomerDTO customerDTO, BindingResult result, Model model) {
		String resetBtn = request.getParameter("resetBtn");
		String saveBtn = request.getParameter("saveBtn");
		
		if (resetBtn != null)
			return "redirect:/customer";
		else if (saveBtn != null) {
			if (result.hasErrors()) {
				model.addAttribute("customer", customerDTO);
				model.addAttribute("titleItems", titleEnum.values());
				return "customer";
			}
			if (customerDTO.getId() == 0) {
				//Add new Customer
				Customer newCustomer = new Customer();
				newCustomer.setName(customerDTO.getName());
				newCustomer.setDateOfBirth(customerDTO.getBirthday());
				newCustomer.setPhone(customerDTO.getPhone());
				newCustomer.setEmail(customerDTO.getEmail());
				newCustomer.setGender((customerDTO.isGender()));
				newCustomer.setAddressLine(customerDTO.getAddress());
				newCustomer.setTitle(customerDTO.getTitle().name());
				
				this.customerService.addCustomer(newCustomer);
				
				//Debug
				System.out.println("Added customer");
			}
			else {
				//Update Customer
				Customer customer = customerService.getCustomerById(customerDTO.getId());
				customer.setName(customerDTO.getName());
				customer.setDateOfBirth(customerDTO.getBirthday());
				customer.setPhone(customerDTO.getPhone());
				customer.setEmail(customerDTO.getEmail());
				customer.setGender((customerDTO.isGender()));
				customer.setAddressLine(customerDTO.getAddress());
				customer.setTitle(customerDTO.getTitle().name());
				this.customerService.updateCustomer(customer);
				
				//Debug
				System.out.println("Updated customer with ID: " + customerDTO.getId());
			}
			
			model.addAttribute("titleItems", titleEnum.values());
			return "redirect:/customers";
		}
		
		return "customer";
	}
//	@RequestMapping(value = "/customer")
//	public String customer(HttpServletRequest request, Model model, @RequestParam(value="selectedIds", required=false) int ids[], @ModelAttribute("customer") @Valid CustomerDTO customerDTO, BindingResult result) {
//		if (result.hasErrors())
//			return "customer";
//		
//		String newBtn = request.getParameter("newBtn");
//		String updateBtn = request.getParameter("updateBtn");
//		String deleteBtn = request.getParameter("deleteBtn");
//		String exportBtn = request.getParameter("exportBtn");
//		String resetBtn = request.getParameter("resetBtn");
//		
//		if (newBtn != null) {
//			model.addAttribute("customer", new CustomerDTO());
//		}
//		else if (updateBtn != null) {
//			if (ids != null && ids.length > 0) {
//				CustomerDTO selectedForUpdateCustomer = customerService.getCustomerById(ids[0]);
//				
////				model.addAttribute("customer", new Customer());
//				model.addAttribute("customer", selectedForUpdateCustomer);
//				
//				//Debug
//				System.out.println("Selected Customer IDs:");
//				for (int i : ids) System.out.println(i);
//			}
//		}
//		else if (resetBtn != null)
//			model.addAttribute("customer", new CustomerDTO());
//		
//		model.addAttribute("titleItems", titleEnum.values());
//		return "customer";
//	}
}
