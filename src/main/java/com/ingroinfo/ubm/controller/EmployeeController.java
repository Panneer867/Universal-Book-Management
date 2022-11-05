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
import com.ingroinfo.ubm.dto.EmployeeDto;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.Employee;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.EmployeeService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/master/employee")
public class EmployeeController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public CompanyService companyService;

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public UserService userService;

	@GetMapping
	public String branch(Model model, Principal principal) {

		model.addAttribute("title", "Branches ");
		model.addAttribute("employee", new EmployeeDto());

		Company company = companyService.findByUser(userService.getUserId(principal.getName()));
		
		model.addAttribute("pe", company.getProfile());
		model.addAttribute("cne", company.getCompanyName());
		
		
		return "/pages/employee_creation";
	}

	@PostMapping
	public String createEmployee(@RequestParam("profile") MultipartFile file,
			@ModelAttribute("employee") EmployeeDto employeeDto, BindingResult bindingResult, Model model)
			throws IOException {

		Employee employee = modelMapper.map(employeeDto, Employee.class);

		// String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		// String fileName = employee.getFirstName() + ".jpg";
		// String uploadDir = "C:/Company/" + company.getCompanyName() +
		// "/Employees/Profile/";
		// employee.setProfile(fileName);
		// employee.setCompany(company);
		// companyService.saveFile(uploadDir, fileName, file);

		employeeService.saveEmployee(employee);

		return "redirect:/master/employee?success";
	}
}
