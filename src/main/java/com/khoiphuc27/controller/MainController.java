package com.khoiphuc27.controller;

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
import com.khoiphuc27.service.CustomerService;

@Controller
public class MainController {
	
//	private AccountService accountService;
	
	@Autowired(required=true)
	private CustomerService customerService;
	
	@RequestMapping("/")
	public String home(Model model) {
		return login(model);
	}	
	
	private String login(Model model) {
		model.addAttribute("account", new Account());
		return "login";
	}
	
	@RequestMapping("/customers")
	public String processingLogin(@ModelAttribute("account") Account account, Model model) {
		if (account == null)
			return "customers";
		
		String userName = account.getUserName();
		String password = account.getPassword();
		
		if (!isInputValidated(userName, password)) {
			model.addAttribute("msg", "Login failed");
			return "redirect:/";
		}	
		else {
			model.addAttribute("listCustomers", customerService.listCustomers());
			return "customers";
		}	
	}

	private boolean isInputValidated(String userName, String password) {
		if (userName.isEmpty() || !userName.equals(userName.toLowerCase()))
			return false;
		if (password.length() < 7)
			return false;
				
		return true;
	}
	
	@RequestMapping("/search")
	public String searchCustomer(@RequestParam("name") String name, @RequestParam("phone")String phone, @RequestParam("birthday") String birthday, @RequestParam("email") String email, @RequestParam("gender") String gender, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("phone", phone);
		model.addAttribute("birthday", birthday);
		model.addAttribute("email", email);
		model.addAttribute("gender", gender);
		
		model.addAttribute("listCustomers", customerService.searchCustomer(name, phone, birthday, email, gender));
		return "customers";
	}
	
}
