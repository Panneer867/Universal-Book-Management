package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.security.Principal;
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
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.configuration.ModelMapperConfig;
import com.ingroinfo.ubm.dao.EmployeeRepository;
import com.ingroinfo.ubm.dto.EmployeeDto;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.Employee;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.EmployeeService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public CompanyService companyService;

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public UserService userService;

	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public BranchService branchService;

	@Autowired
	public ModelMapperConfig mapper;

	@GetMapping
	public String branch(Model model, Principal principal) {

		model.addAttribute("title", "Branches ");
		model.addAttribute("employee", new EmployeeDto());
		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("emailExists", "You have entered an email address that already exists!");
		model.addAttribute("mobileExists", "You have entered an mobile number that already exists!");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);

		if (company != null) {
			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("branchCount", company.getNoOfBranch());
			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
		}

		return "/management/employee_creation";
	}

	@PostMapping
	public String createEmployee(@RequestParam("profile") MultipartFile file,
			@ModelAttribute("employee") EmployeeDto employeeDto, BindingResult bindingResult, Model model,
			Principal principal) throws IOException {

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);

		if (employeeService.emailExists(employeeDto)) {
			return "redirect:/employee/management?emailAlreadyExists";
		}

		if (employeeService.mobileExists(employeeDto)) {
			return "redirect:/employee/management?mobileAlreadyExists";
		}

		Employee employee = modelMapper.map(employeeDto, Employee.class);

		if (company != null) {

			String fileName = employee.getFirstName() + ".jpg";
			String uploadDir = "C:/Company/" + company.getCompanyName() + "/Employees/";
			employee.setProfile(fileName);
			employee.setCompanyId(company.getCompanyId());
			companyService.saveFile(uploadDir, fileName, file);
		}
		employeeService.saveEmployee(employee);

		return "redirect:/employee?success";
	}

	@GetMapping("/management")
	public String employeeManagement(Model model, Principal principal) {

		model.addAttribute("title", "Employee Management ");
		model.addAttribute("update", new EmployeeDto());
		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("employees", employeeService.getAllEmployees());
		model.addAttribute("emailExists", "You have entered an email address that already exists!");
		model.addAttribute("mobileExists", "You have entered an mobile number that already exists!");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);

		if (company != null) {

			model.addAttribute("companyId", company.getCompanyId());
			model.addAttribute("companyName", company.getCompanyName());
			model.addAttribute("branchCount", company.getNoOfBranch());
			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		}

		return "/management/employee_management";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("update") EmployeeDto employeeDto, Principal principal, Model model) {

		Employee employee = employeeRepository.findByEmployeeId(employeeDto.getEmployeeId());

		mapper.modelMapper().map(employeeDto, employee);

		if (employeeService.emailCheck(employee)) {
			return "redirect:/employee/management?emailAlreadyExists";
		}

		if (employeeService.mobileCheck(employee)) {
			return "redirect:/employee/management?mobileAlreadyExists";
		}

		employeeService.saveEmployee(employee);

		return "redirect:/employee/management?employeeUpdated";
	}

	@GetMapping("/delete")
	public String deleteEmpmloyee(@RequestParam Long employeeId) {

		employeeService.deleteByEmployeeId(employeeId);

		return "redirect:/employee/management?employeeDeleted";

	}
}
