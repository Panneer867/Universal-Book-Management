package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ingroinfo.ubm.dto.UserDto;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.EmployeeService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/master/user")
public class UserContoller {

	@Autowired
	public CompanyService companyService;

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public BranchService branchService;

	@Autowired
	public UserService userService;

	@GetMapping
	public String userManagement(Model model, Principal principal) {
		model.addAttribute("title", "User Registration");
		model.addAttribute("emailExists", "You have entered an email address that already exists! ");
		model.addAttribute("mobileExists", "You have entered an mobile number that already exists! ");
		model.addAttribute("usernameExists", "You have entered an username that already exists! ");

		model.addAttribute("user", new UserDto());
		String userName = principal.getName();

		User user = userService.getUserId(userName);
		Company company = companyService.findByUser(user);
		if (company == null) {
			Branch branch = branchService.findByUserId(user);
			Company cmpy = branch.getCompany();
			model.addAttribute("companyId", cmpy.getCompanyId());
			model.addAttribute("companyName", cmpy.getCompanyName());
		} else {
			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
		}

		List<Branch> branches = branchService.getAllBranches();
		model.addAttribute("branches", branches);

		return "/pages/user_creation";
	}

	@PostMapping
	public String createUser(@ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model)
			throws IOException {

		if (userService.emailExists(userDto)) {
			return "redirect:/master/user?emailExists";
		}

		if (userService.mobileExists(userDto)) {
			return "redirect:/master/user?mobileExists";
		}

		if (userService.usernameExists(userDto)) {
			return "redirect:/master/user?usernameExists";
		}

		userService.saveUser(userDto);
		return "redirect:/master/user?success";
	}

	@GetMapping("/management")
	public String userDetails(Model model, Principal principal) {
		model.addAttribute("title", "User Management");

		Company company = companyService.findByUser(userService.getUserId(principal.getName()));

		if (company != null) {

			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
		}

		List<UserDto> users = userService.getAllUsers();

		for (Iterator<UserDto> it = users.iterator(); it.hasNext();) {
			if (it.next().getUserType().equalsIgnoreCase("Branch Admin"))
				it.remove();
		}

		model.addAttribute("users", users);

		return "/pages/user_management";
	}

	@GetMapping("/deleteUser")
	public String deleteUsers(@RequestParam Long userId) {

		userService.deleteByUserId(userId);

		return "redirect:/master/user/management?userDeleted";

	}
}
