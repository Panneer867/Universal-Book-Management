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
@RequestMapping("/master/user")
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
		Company company = companyService.findByUser(userService.getUserId(principal.getName()));
		Branch branch = branchService.findByUserId(userService.getUserId(principal.getName()));

		if (branch != null) {
			Company cmpy = branch.getCompany();
			model.addAttribute("companyId", cmpy.getCompanyId());
			model.addAttribute("companyName", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());

		} else if (company != null) {
			model.addAttribute("companyProfile", "enableCompany");
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
		model.addAttribute("updateUser", new UserDto());
		model.addAttribute("emailExists", "You have entered an email address that already exists! ");
		model.addAttribute("mobileExists", "You have entered an mobile number that already exists! ");
		model.addAttribute("usernameExists", "You have entered an username that already exists! ");

		Company company = companyService.findByUser(userService.getUserId(principal.getName()));

		if (company != null) {
			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
		} else {
			Branch branch = branchService.findByUserId(userService.getUserId(principal.getName()));
			Company cmpy = branch.getCompany();
			model.addAttribute("branchProfile", "enableBranch");
			model.addAttribute("companyId", cmpy.getCompanyId());
			model.addAttribute("companyName", cmpy.getCompanyName());

		}

		List<UserDto> users = userService.getAllUsers();

		for (Iterator<UserDto> it = users.iterator(); it.hasNext();) {
			if (it.next().getUserType().equalsIgnoreCase("BRANCH ADMIN"))
				it.remove();
		}

		model.addAttribute("users", users);

		List<Branch> branches = branchService.getAllBranches();
		model.addAttribute("branches", branches);

		return "/pages/user_management";
	}

	@PostMapping("/update")
	public String userUpdate(@ModelAttribute("updateUser") UserDto userDto, Model model) {

		User user = userRepository.findByUserId(userDto.getUserId());
		String password = user.getPassword();
		mapper.modelMapper().map(userDto, user);

		if (userService.emailCheck(user)) {
			return "redirect:/master/user/management?emailAlreadyExists";
		}

		if (userService.mobileCheck(user)) {
			return "redirect:/master/user/management?mobileAlreadyExists";
		}

		List<User> userList = userRepository.findAll();

		List<User> filteredList = userList.stream().filter(x -> !user.getEmail().equals(x.getEmail()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getUsername().equals(userDto.getUsername())).findFirst()
				.isPresent();

		if (isExists) {

			return "redirect:/master/user/management?usernameAlreadyExists";
		}

		if (userDto.getPassword().equalsIgnoreCase("")) {
			user.setPassword(password);
		} else {
			user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		}

		userRepository.save(user);

		return "redirect:/master/user/management?detailsUpdated";

	}

	@GetMapping("/delete")
	public String deleteUsers(@RequestParam Long userId) {

		userService.deleteByUserId(userId);

		return "redirect:/master/user/management?userDeleted";

	}
}
