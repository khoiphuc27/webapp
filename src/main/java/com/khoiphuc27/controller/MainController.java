package com.khoiphuc27.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.khoiphuc27.model.Account;
import com.khoiphuc27.model.Customer;
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
	public String customers(Model model) {
		model.addAttribute("listCustomers", customerService.listCustomers());
		model.addAttribute("gender", "Male");
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
	public String searchCustomer(@RequestParam("name") String name, @RequestParam("phone")String phone, @RequestParam("birthday") String birthday, @RequestParam("email") String email, @RequestParam("gender") String gender, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("phone", phone);
		model.addAttribute("birthday", birthday);
		model.addAttribute("email", email);
		model.addAttribute("gender", gender);
		
		if (validateInputSearch(name, phone, email, model))
			model.addAttribute("listCustomers", customerService.searchCustomer(name, phone, birthday, email, gender));
		return "customers";
	}
	
	private boolean validateInputSearch(String name, String phone, String email, Model model) {
		String invalidInputNoti = "";
		boolean checkName, checkPhone, checkEmail;
		checkName = checkPhone = checkEmail = true;
		
		if (name.length() > 255) {
			invalidInputNoti += "Name length is exceed 255 characters.\n";
			checkName = false;
		}
		if (phone.length() > 255) {
			invalidInputNoti += "Phone length is exceed 255 characters.\n";
			checkPhone = false;
		}
		if (!email.isEmpty() && !checkEmailPattern(email)) {
			invalidInputNoti += "Not valid email pattern.\n";
			checkEmail = false;
		}
		
		model.addAttribute("invalidInputNoti", invalidInputNoti);
		return checkName && checkPhone && checkEmail ;
	}

	private boolean checkEmailPattern(String email) {
		Pattern pattern = Pattern.compile("^.+@.+\\..+$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	@RequestMapping(value = "/customer")
	public String customer(HttpServletRequest request, Model model, @RequestParam(value="selectedIds", required=false) int ids[]) {
		String newBtn = request.getParameter("newBtn");
		String updateBtn = request.getParameter("updateBtn");
		String deleteBtn = request.getParameter("deleteBtn");
		String exportBtn = request.getParameter("exportBtn");
		
		if (newBtn != null) {
			model.addAttribute("gender", "Male");
			model.addAttribute("customer", new Customer());
		}
		else if (updateBtn != null) {
			if (ids != null && ids.length > 0) {
				Customer selectedForUpdateCustomer = customerService.getCustomerById(ids[0]);
				model.addAttribute("customer", selectedForUpdateCustomer);
				
				//Debug
				System.out.println("Selected Customer IDs:");
				for (int i : ids) System.out.println(i);
			}
		}
		
		return "customer";
	}
	
	
//	@RequestMapping(value = "/customer")
//	public String customer(Model model) {
//		if (!model.containsAttribute("customer")) {
//			model.addAttribute("gender", "Male");
//			model.addAttribute("customer", new Customer());
//		}
//		return "customer";
//	}
//	
//	@RequestMapping(params = "new", method = RequestMethod.POST)
//	public String newCustomer(HttpServletRequest request) {
//		return "redirect:/customer";
//	}
//	@RequestMapping(params = "update", method = RequestMethod.POST)
//	public String update(HttpServletRequest request, @RequestParam(value = "selectedIds") int ids[]) {
//		
//		//Debug
//		System.out.println("Selected IDs:");
//		for (int i : ids) System.out.println(i);
//		return "redirect:/customer";
//	}
//	
}
