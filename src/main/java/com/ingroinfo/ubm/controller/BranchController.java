package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ingroinfo.ubm.dto.BranchDto;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.EmployeeService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/master/branch")
public class BranchController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public CompanyService companyService;

	@Autowired
	public BranchService branchService;

	@Autowired
	public UserService userService;

	@Autowired
	public EmployeeService employeeService;

	@GetMapping
	public String branch(Model model, Principal principal) {

		Company company = companyService.findByUser(userService.getUserId(principal.getName()));
		Branch branch = branchService.findByUserId(userService.getUserId(principal.getName()));

		model.addAttribute("title", "Branch Management");
		model.addAttribute("emailExists", "You have entered an email address that already exists!");
		model.addAttribute("mobileExists", "You have entered an mobile number that already exists!");
		model.addAttribute("usernameExists", "You have entered an username that already exists!");
		model.addAttribute("branch", new BranchDto());
		model.addAttribute("stateList", userService.getAllStates());

		if (company != null) {

			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("branchCount", company.getNoOfBranch());
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("branchProfile", "enableBranch");
			model.addAttribute("companyId", cmpy.getCompanyId());
			model.addAttribute("companyName", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());
		}

		return "/pages/branch_creation";
	}

	@PostMapping
	public String createBranch(@ModelAttribute("branch") BranchDto branchDto, BindingResult bindingResult,
			Principal principal, Model model) throws IOException {

		if (branchService.emailExists(branchDto)) {
			return "redirect:/master/branch?emailExists";
		}

		if (branchService.mobileExists(branchDto)) {
			return "redirect:/master/branch?mobileExists";
		}

		if (branchService.usernameExists(branchDto)) {
			return "redirect:/master/branch?usernameExists";
		}

		Branch branch = modelMapper.map(branchDto, Branch.class);

		String userName = principal.getName();
		User userId = userService.getUserId(userName);
		Company company = companyService.findByUser(userId);
		branch.setCompany(company);

		if (branchService.branchAllowed(company)) {
			branchService.userDetails(branchDto);
			branchService.saveBranch(branch);
			userService.saveBranchId(branch);
		} else {
			return "redirect:/master/branch?notAllowed";
		}

		return "redirect:/master/branch?success";
	}

	@GetMapping("/management")
	public String branchManagement(Model model, Principal principal) {

		Company company = companyService.findByUser(userService.getUserId(principal.getName()));

		if (company != null) {
			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
		}

		List<BranchDto> branches = branchService.getAllBranch();

		model.addAttribute("branches", branches);

		return "/pages/branch_management";
	}

	@GetMapping("/deleteBranch")
	public String deleteUsers(@RequestParam Long branchId) {

		Branch branch = branchService.findByBranchId(branchId);
		User user = branch.getUser();

		branchService.deleteByBranchId(branchId);
		userService.deleteByUserId(user.getId());

		return "redirect:/master/branch/management?branchDeleted";

	}

	@GetMapping("/profile")
	public String branchProfile(Model model, Principal principal) {

		model.addAttribute("title", "Branch Profile");

		Branch branch = branchService.findByUserId(userService.getUserId(principal.getName()));
		Company company = branch.getCompany();

		model.addAttribute("branchProfile", "enableBranch");
		model.addAttribute("companyId", company.getCompanyId());
		model.addAttribute("companyName", company.getCompanyName());
		model.addAttribute("usernameofbranch", branch.getFirstName());

		return "/pages/branch_profile";
	}

}
