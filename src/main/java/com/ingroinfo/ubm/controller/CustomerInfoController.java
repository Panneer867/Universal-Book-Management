package com.ingroinfo.ubm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.ubm.dto.CustomerInfoDto;
import com.ingroinfo.ubm.service.CustomerInfoService;

@Controller
@RequestMapping("/master")
public class CustomerInfoController {

	@Autowired
	private CustomerInfoService customerInfoService;

	@GetMapping("/customer-info")
	public String customerInfo(Model model) {
		model.addAttribute("title", "Customer Information");
		return "/pages/customer_info";
	}

	@PostMapping("/saveCustomerData")
	public String saveCustomerData(Model model, CustomerInfoDto customerInfoDto) {

		customerInfoService.createCustomerInfo(customerInfoDto);
		return "redirect:/master/customer-info";

	}
}
