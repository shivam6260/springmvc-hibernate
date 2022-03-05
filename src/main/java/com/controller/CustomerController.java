package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DAO.CustomerDAO;
import com.Entity.Customer;
import com.Service.CustomerService;
import com.Service.CustomerServiceImplementation;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		List<Customer> theCustomers = customerService.getCustomers();

//		Collections.sort(theCustomers, new Comparator<Customer>() {
//
//			public int compare(Customer c1, Customer c2) {
//				return c1.getFirstName().compareTo(c2.getFirstName());
//			}
//		});
		
		Collections.sort(theCustomers, new Comparator<Customer>() {
			public int compare(Customer c1, Customer c2) {
				return c2.getId() - c1.getId();
			}
		});

		System.out.println(theCustomers);
		theModel.addAttribute("customers", theCustomers);

		return "list-customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAddingCustomer(Model theModel) {
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate( @RequestParam("customerId") int theId, Model theModel)
	{
		Customer theCustomer = customerService.getCustomer(theId);
		
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}

	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId)
	{
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
}




















