package com.ingroinfo.ubm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/master/dashboard")
public class DashboardController {

	@Autowired
	public CompanyService companyService;

	@Autowired
	public BranchService branchService;

	@Autowired
	public UserService userService;

	@GetMapping
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("title", "Welcome to Universal Book Management");

		Company company = companyService.findByUser(userService.getUserId(principal.getName()));
		Branch branch = branchService.findByUserId(userService.getUserId(principal.getName()));

		if (company != null) {
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
		} else if (branch != null) {
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "dashboard";
	}

}
