package com.khoiphuc27.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khoiphuc27.dto.CustomerDTO;
import com.khoiphuc27.model.Account;
import com.khoiphuc27.model.Customer;
import com.khoiphuc27.model.titleEnum;
import com.khoiphuc27.service.AccountService;
import com.khoiphuc27.service.CustomerService;

@Controller
public class MainController {
	@Autowired(required=true)
	private AccountService accountService;
	
	@Autowired(required=true)
	private CustomerService customerService;
	
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
	public String searchCustomer(@ModelAttribute("customer") @Valid Customer customer, BindingResult result, Model model) {
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

	@RequestMapping(value = "/customer")
	public String customer(HttpServletRequest request, Model model, @RequestParam(value="selectedIds", required=false) int ids[], @ModelAttribute("customer") CustomerDTO customerDTO) {
		String newBtn = request.getParameter("newBtn");
		String updateBtn = request.getParameter("updateBtn");
		String deleteBtn = request.getParameter("deleteBtn");
		String exportBtn = request.getParameter("exportBtn");
		String resetBtn = request.getParameter("resetBtn");
		
		if (newBtn != null) {
			model.addAttribute("customer", new CustomerDTO());
		}
		else if (updateBtn != null) {
			if (ids != null && ids.length > 0) {
				CustomerDTO selectedForUpdateCustomer = customerService.getCustomerById(ids[0]);
				
//				model.addAttribute("customer", new Customer());
				model.addAttribute("customer", selectedForUpdateCustomer);
				
				//Debug
				System.out.println("Selected Customer IDs:");
				for (int i : ids) System.out.println(i);
			}
		}
		else if (resetBtn != null)
			model.addAttribute("customer", new CustomerDTO());
		
		model.addAttribute("titleItems", titleEnum.values());
		return "customer";
	}
}
