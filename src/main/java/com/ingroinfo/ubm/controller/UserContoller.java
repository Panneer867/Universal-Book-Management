package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
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
import com.ingroinfo.ubm.dao.RoleRepository;
import com.ingroinfo.ubm.dao.UserRepository;
import com.ingroinfo.ubm.dto.UserDto;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.EmployeeService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserContoller {

	@Autowired
	public ModelMapperConfig mapper;

	@Autowired
	public CompanyService companyService;

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public BranchService branchService;

	@Autowired
	public UserService userService;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String userManagement(Model model, Principal principal) {

		model.addAttribute("title", "User Registration");
		model.addAttribute("emailExists", "You have entered an email address that already exists! ");
		model.addAttribute("mobileExists", "You have entered an mobile number that already exists! ");
		model.addAttribute("usernameExists", "You have entered an username that already exists! ");

		model.addAttribute("user", new UserDto());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		List<Branch> branches = branchService.getAllBranches();

		if (company != null) {
			model.addAttribute("companyProfile", "enableCompany");
			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("enable", "userRole");

			model.addAttribute("branches", branches);
		} else if (branch != null) {
			Company cmpy = branch.getCompany();
			model.addAttribute("enable", "userRole");
			model.addAttribute("branchProfile", "enableBranch");
			model.addAttribute("companyId", cmpy.getCompanyId());
			model.addAttribute("companyName", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());

			List<Branch> filteredList = branches.stream().filter(x -> branch.getBranchId().equals(x.getBranchId()))
					.collect(Collectors.toList());
			model.addAttribute("branches", filteredList);
		} else {

			Branch branch1 = branchService.findByBranchId(user.getBranchId());
			Company cpy = branch1.getCompany();
			List<Branch> filteredList = branches.stream().filter(x -> branch1.getBranchId().equals(x.getBranchId()))
					.collect(Collectors.toList());
			model.addAttribute("enable", null);
			model.addAttribute("branches", filteredList);
			model.addAttribute("companyId", cpy.getCompanyId());
			model.addAttribute("companyName", cpy.getCompanyName());
			model.addAttribute("userProfile", "enableBranch");
			model.addAttribute("usernameofuser", user.getFirstName());
		}

		return "/management/user_creation";
	}

	@PostMapping
	public String createUser(@ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model)
			throws IOException {

		if (userService.emailExists(userDto)) {
			return "redirect:/user?emailExists";
		}

		if (userService.mobileExists(userDto)) {
			return "redirect:/user?mobileExists";
		}

		if (userService.usernameExists(userDto)) {
			return "redirect:/user?usernameExists";
		}

		userService.saveUser(userDto);
		return "redirect:/user?success";
	}

	@GetMapping("/management")
	public String userDetails(Model model, Principal principal) {

		model.addAttribute("title", "User Management");
		model.addAttribute("updateUser", new UserDto());
		List<UserDto> users = userService.getAllUsers();
		model.addAttribute("emailExists", "You have entered an email address that already exists! ");
		model.addAttribute("mobileExists", "You have entered an mobile number that already exists! ");
		model.addAttribute("usernameExists", "You have entered an username that already exists! ");

		User user = userService.getUserId(principal.getName());
		List<Branch> branches = branchService.getAllBranches();

		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {
			model.addAttribute("companyProfile", "enableCompany");
			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("branches", branches);

			for (Iterator<UserDto> it = users.iterator(); it.hasNext();) {
				if (it.next().getUserType().equalsIgnoreCase("BRANCH ADMIN"))
					it.remove();
			}

			model.addAttribute("users", users);
		} else if (branch != null) {
			Company cmpy = branch.getCompany();
			model.addAttribute("branches", branches);
			model.addAttribute("branchProfile", "enableBranch");
			model.addAttribute("companyId", cmpy.getCompanyId());
			model.addAttribute("companyName", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());

			List<Branch> filteredList = branches.stream().filter(x -> branch.getBranchId().equals(x.getBranchId()))
					.collect(Collectors.toList());

			model.addAttribute("branches", filteredList);

			for (Iterator<UserDto> it = users.iterator(); it.hasNext();) {
				if (it.next().getUserType().equalsIgnoreCase("BRANCH ADMIN"))
					it.remove();
			}

			List<UserDto> userList = users.stream().filter(x -> branch.getBranchId().equals(x.getBranchId()))
					.collect(Collectors.toList());

			model.addAttribute("users", userList);

		} else {

			Branch branch1 = branchService.findByBranchId(user.getBranchId());
			Company cpy = branch1.getCompany();
			List<Branch> filteredList = branches.stream().filter(x -> branch1.getBranchId().equals(x.getBranchId()))
					.collect(Collectors.toList());
			model.addAttribute("branches", filteredList);
			model.addAttribute("companyId", cpy.getCompanyId());
			model.addAttribute("companyName", cpy.getCompanyName());
			model.addAttribute("userProfile", "enableBranch");
			model.addAttribute("usernameofuser", user.getFirstName());

			for (Iterator<UserDto> it = users.iterator(); it.hasNext();) {
				if (it.next().getUserType().equalsIgnoreCase("BRANCH ADMIN"))
					it.remove();
			}

			for (Iterator<UserDto> it = users.iterator(); it.hasNext();) {
				if (it.next().getUserType().equalsIgnoreCase("BRANCH USER"))
					it.remove();
			}

			List<UserDto> userList = users.stream().filter(x -> branch1.getBranchId().equals(x.getBranchId()))
					.collect(Collectors.toList());

			model.addAttribute("users", userList);
		}

		return "/management/user_management";
	}

	@PostMapping("/update")
	public String userUpdate(@ModelAttribute("updateUser") UserDto userDto, Model model) {

		User user = userRepository.findByUserId(userDto.getUserId());
		String password = user.getPassword();
		mapper.modelMapper().map(userDto, user);

		if (userService.emailCheck(user)) {
			return "redirect:/user/management?emailAlreadyExists";
		}

		if (userService.mobileCheck(user)) {
			return "redirect:/user/management?mobileAlreadyExists";
		}

		List<User> userList = userRepository.findAll();

		List<User> filteredList = userList.stream().filter(x -> !user.getEmail().equals(x.getEmail()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getUsername().equals(userDto.getUsername())).findFirst()
				.isPresent();

		if (isExists) {

			return "redirect:/user/management?usernameAlreadyExists";
		}

		if (userDto.getPassword().equalsIgnoreCase("")) {
			user.setPassword(password);
		} else {
			user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		}

		userRepository.save(user);
		return "redirect:/user/management?userUpdated";
	}

	@GetMapping("/delete")
	public String deleteUsers(@RequestParam Long userId) {

		userService.deleteByUserId(userId);
		return "redirect:/user/management?userDeleted";

	}
}
