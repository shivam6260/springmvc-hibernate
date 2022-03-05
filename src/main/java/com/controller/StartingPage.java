package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Entity.Customer;

@Controller
public class StartingPage {
	
	@RequestMapping("/")
	public String listCustomers() {
		return "index";
	}

}
