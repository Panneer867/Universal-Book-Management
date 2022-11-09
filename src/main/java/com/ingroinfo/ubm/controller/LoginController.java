package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingroinfo.ubm.dao.CompanyRepository;
import com.ingroinfo.ubm.dao.EmployeeRepository;
import com.ingroinfo.ubm.dao.UserRepository;
import com.ingroinfo.ubm.dto.BranchDto;
import com.ingroinfo.ubm.dto.CompanyDto;
import com.ingroinfo.ubm.dto.UserDto;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.Employee;
import com.ingroinfo.ubm.entity.State;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.UserService;

@Controller
public class LoginController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public CompanyService companyService;

	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public UserService userService;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public BranchService branchService;

	@Autowired
	private CompanyRepository companyRepository;

	@GetMapping("/login")
	public String login(Model model) {

		if (companyService.companyExists()) {
			model.addAttribute("enable", null);
		} else {
			model.addAttribute("enable", "enable");
		}

		return "login";
	}

	@GetMapping("/logout")
	public String logout() {

		return "login";
	}

	@GetMapping("/access-denied")
	public String error() {

		return "access_denied";
	}

	@GetMapping("/server-error")
	public String serverError() {

		return "server_error";
	}

	@GetMapping("/company/register")
	public String company(Model model) {

		model.addAttribute("title", "Company Creation");
		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("bankList", userService.getAllBanks());
		model.addAttribute("company", new CompanyDto());

		Company company = companyRepository.findTopByOrderByCompanyIdDesc();

		if (company != null) {
			model.addAttribute("companyNo", company.getCompanyId());
		}
		return "/company_creation";
	}

	@PostMapping("/company/register")
	public String createCompany(@RequestParam("companyLogo") MultipartFile file1,
			@RequestParam("profile") MultipartFile file2, @ModelAttribute("company") CompanyDto companyDto,
			BindingResult bindingResult) throws IOException {

		Company company = modelMapper.map(companyDto, Company.class);

		String companyLogo = StringUtils.cleanPath(file1.getOriginalFilename());
		// String profile = StringUtils.cleanPath(file2.getOriginalFilename());
		String profile = company.getFirstName() + ".jpg";
		String uploadDir = "C:/Company/" + company.getCompanyName() + "/Logo";
		String uploadDir1 = "C:/Company/" + company.getCompanyName() + "/Owner_Profile";

		company.setCompanyPath("C:/Company/" + company.getCompanyName() + "/");
		company.setCompanyLogo(companyLogo);
		company.setProfile(profile);

		companyService.saveFile(uploadDir, companyLogo, file1);
		companyService.saveFile(uploadDir1, profile, file2);

		companyService.userDetails(companyDto);
		companyService.saveCompany(company);

		return "redirect:/company/register?success";
	}

	@GetMapping("/getCities")
	public @ResponseBody String getCities(@RequestParam String stateName) {

		State state = userService.findById(stateName);
		Integer stateId = state.getId();

		String json = null;
		List<Object[]> list = userService.getCitiesByState(stateId);
		try {
			json = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@ResponseBody
	@RequestMapping("/get")
	public BranchDto getBranch(@RequestParam Long id) {

		Optional<Branch> branch = branchService.getBranchById(id);

		BranchDto branchDto = modelMapper.map(branch, BranchDto.class);

		branchDto.setUsername(branch.get().getUser().getUsername());

		return branchDto;
	}

	@ResponseBody
	@RequestMapping("/get/user")
	public UserDto getUser(@RequestParam Long id) {

		User user = userRepository.findByUserId(id);

		String type = user.getUserType();

		if (type.equalsIgnoreCase("NORMAL USER")) {
			user.setUserType("ROLE_USER");
		} else if (type.equalsIgnoreCase("BRANCH USER")) {
			user.setUserType("ROLE_BRANCH");
		}

		return modelMapper.map(user, UserDto.class);
	}

	@ResponseBody
	@RequestMapping("/get/employee")
	public Employee getEmployee(@RequestParam Long id) {

		return employeeRepository.findByEmployeeId(id);
	}
}
