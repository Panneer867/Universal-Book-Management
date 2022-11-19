package com.ingroinfo.ubm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/dashboard")
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

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
		} else if (branch != null) {
			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		} else {
			model.addAttribute("usernameofuser", user.getFirstName());
			model.addAttribute("userProfile", "enableUser");
		}

		return "/main/dashboard";
	}

}
