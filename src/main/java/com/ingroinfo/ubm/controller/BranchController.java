package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ingroinfo.ubm.configuration.ModelMapperConfig;
import com.ingroinfo.ubm.dao.UserRepository;
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
	public UserRepository userRepository;

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public ModelMapperConfig mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String branch(Model model, Principal principal) {

		model.addAttribute("title", "Branch Creation");
		model.addAttribute("emailExists", "You have entered an email address that already exists!");
		model.addAttribute("mobileExists", "You have entered an mobile number that already exists!");
		model.addAttribute("usernameExists", "You have entered an username that already exists!");
		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("branch", new BranchDto());

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("branchCount", company.getNoOfBranch());
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("companyId", cmpy.getCompanyId());
			model.addAttribute("companyName", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("branchProfile", "enableBranch");
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
		User user = modelMapper.map(branchDto, User.class);

		Company company = companyService.findByUser(userService.getUserId(principal.getName()));
		branch.setCompany(company);

		if (branchService.branchAllowed(company)) {
			branchService.saveBranch(branch);
			branchService.userDetails(user);
		} else {
			return "redirect:/master/branch?notAllowed";
		}

		return "redirect:/master/branch?success";
	}

	@GetMapping("/management")
	public String branchManagement(Model model, Principal principal) {

		model.addAttribute("title", "Branch Management");
		model.addAttribute("usernameExists", "You have entered an username that already exists!");
		model.addAttribute("emailExists", "You have entered an email that already exists!");
		model.addAttribute("mobileExists", "You have entered an mobile that already exists!");
		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("branches", branchService.getAllBranch());
		model.addAttribute("update", new BranchDto());

		Company company = companyService.findByUser(userService.getUserId(principal.getName()));

		if (company != null) {

			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("branchCount", company.getNoOfBranch());
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
		}

		return "/pages/branch_management";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("update") BranchDto branchDto, Principal principal, Model model) {

		Branch branch = branchService.findByBranchId(branchDto.getBranchId());

		User user = branch.getUser();
		Long userId = user.getUserId();
		String password = user.getPassword();

		mapper.modelMapper().map(branchDto, branch);
		mapper.modelMapper().map(branchDto, user);

		user.setUserId(userId);

		if (userService.emailCheck(user)) {
			return "redirect:/master/branch/management?emailAlreadyExists";
		}

		if (userService.mobileCheck(user)) {
			return "redirect:/master/branch/management?mobileAlreadyExists";
		}

		List<User> userList = userRepository.findAll();

		List<User> filteredList = userList.stream().filter(x -> !user.getEmail().equals(x.getEmail()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getUsername().equals(branchDto.getUsername()))
				.findFirst().isPresent();

		if (isExists) {

			return "redirect:/master/branch/management?usernameAlreadyExists";
		}

		if (branchDto.getPassword().equalsIgnoreCase("")) {

			user.setPassword(password);

		} else {

			user.setPassword(this.passwordEncoder.encode(branchDto.getPassword()));
		}
		userService.editUser(user);
		branchService.updateBranch(branch);

		return "redirect:/master/branch/management?branchUpdated";
	}

	@GetMapping("/profile")
	public String branchProfile(Model model, Principal principal) {

		model.addAttribute("title", "Branch Profile");
		model.addAttribute("wrong", "You have entered wrong password!!");
		model.addAttribute("profile", "Profile has been sucessfully updated !");
		model.addAttribute("changed", "Password has been sucessfully updated !");
		model.addAttribute("usernameExists", "You have entered an username that already exists!");
		model.addAttribute("emailExists", "You have entered an email that already exists!");
		model.addAttribute("mobileExists", "You have entered an mobile that already exists!");

		Branch branch = branchService.findByUserId(userService.getUserId(principal.getName()));
		Company company = branch.getCompany();

		model.addAttribute("branch", branch);
		model.addAttribute("update", new BranchDto());
		model.addAttribute("brchId", branch.getBranchId());
		model.addAttribute("branchProfile", "enableBranch");
		model.addAttribute("username", principal.getName());
		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("companyName", company.getCompanyName());
		model.addAttribute("usernameofbranch", branch.getFirstName());

		return "/pages/branch_profile";
	}

	@PostMapping("/profile")
	public String branchUpdate(@ModelAttribute("update") BranchDto branchDto, Principal principal, Model model)
			throws IOException {

		User user = userRepository.findByUsername(principal.getName());
		Branch branch = branchService.findByUserId(user);

		mapper.modelMapper().map(branchDto, branch);
		mapper.modelMapper().map(branchDto, user);

		if (userService.emailCheck(user)) {
			return "redirect:/master/branch/profile?emailAlreadyExists";
		}

		if (userService.mobileCheck(user)) {
			return "redirect:/master/branch/profile?mobileAlreadyExists";
		}

		List<User> userList = userRepository.findAll();

		List<User> filteredList = userList.stream().filter(x -> !user.getEmail().equals(x.getEmail()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getUsername().equals(branchDto.getUsername()))
				.findFirst().isPresent();

		if (isExists) {
			return "redirect:/master/branch/profile?usernameExists";
		}

		userService.editUser(user);
		branchService.editBranch(branch);

		return "redirect:/master/branch/profile?profileUpdated";

	}

	@PostMapping("/userDetails")
	public String userDetails(@ModelAttribute("user") BranchDto branchDto, Principal principal, Model model) {

		User user = userRepository.findByUsername(principal.getName());

		if (this.passwordEncoder.matches(branchDto.getPassword(), user.getPassword())) {

			List<User> userList = userRepository.findAll();

			List<User> filteredList = userList.stream().filter(x -> !user.getEmail().equals(x.getEmail()))
					.collect(Collectors.toList());

			boolean isExists = filteredList.stream().filter(o -> o.getUsername().equals(branchDto.getUsername()))
					.findFirst().isPresent();

			if (isExists) {
				return "redirect:/master/branch/profile?usernameExists";
			}

			user.setUsername(branchDto.getUsername());

			if (branchDto.getNewPassword().equalsIgnoreCase("")) {
				userRepository.save(user);
			} else {
				user.setPassword(this.passwordEncoder.encode(branchDto.getNewPassword()));
				userRepository.save(user);
			}

			return "redirect:/login?userDetailsChanged";

		} else {

			return "redirect:/master/branch/profile?wrongPassword";
		}

	}

	@GetMapping("/delete")
	public String deleteBranch(@RequestParam Long branchId) {

		branchService.deleteByBranchId(branchId);
		userRepository.deleteByBranchAssociatedUsers(branchId);

		return "redirect:/master/branch/management?branchDeleted";

	}

}
