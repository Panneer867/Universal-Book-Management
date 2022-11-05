package com.ingroinfo.ubm.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.configuration.ModelMapperConfig;
import com.ingroinfo.ubm.dao.CompanyRepository;
import com.ingroinfo.ubm.dao.UserRepository;
import com.ingroinfo.ubm.dto.CompanyDto;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/company")
public class CompanyController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ModelMapperConfig mapper;

	@Autowired
	public CompanyService companyService;

	@Autowired
	public UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/register")
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

	@PostMapping("/register")
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

	@GetMapping("/profile")
	public String showProfile(Model model, Principal principal) {

		User user = userRepository.findByUsername(principal.getName());
		Company company = companyService.findByUser(user);

		model.addAttribute("pe", company.getProfile());
		model.addAttribute("cne", company.getCompanyName());
		model.addAttribute("details", company);
		model.addAttribute("username", principal.getName());
		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("bankList", userService.getAllBanks());
		model.addAttribute("update", new CompanyDto());
		model.addAttribute("profile", "Profile has been sucessfully updated !");
		model.addAttribute("changed", "Password has been sucessfully updated !");
		model.addAttribute("wrong", "You have entered wrong password!!");

		return "/pages/company_details";

	}

	@PostMapping("/profile")
	public String companyUpdate(@ModelAttribute("company") CompanyDto companyDto, Principal principal, Model model)
			throws IOException {

		User user = userRepository.findByUsername(principal.getName());
		Company company = companyService.findByUser(user);

		companyService.saveOldData(company);

		String oldfolder = "C:/Company/" + company.getCompanyName() + "/";
		File file = new File(oldfolder);

		mapper.modelMapper().map(companyDto, company);
		mapper.modelMapper().map(companyDto, user);

		boolean usernameChanged = userService.usernameChanged(user);

		String folder = "C:/Company/" + company.getCompanyName() + "/";

		if (!oldfolder.equalsIgnoreCase(folder)) {
			File rename = new File(folder);
			file.renameTo(rename);
			company.setCompanyPath(folder);
		}

		userService.editUser(user);
		companyService.editCompany(company);

		if (usernameChanged) {
			return "redirect:/login?usernameChanged";
		}

		return "redirect:/company/profile?profileUpdated";
	}

	@PostMapping("/profile/picture")
	public String companyUpdate(@RequestParam("profile") MultipartFile file,
			@ModelAttribute("company") CompanyDto companyDto, BindingResult bindingResult, Principal principal,
			Model model) throws IOException {

		User user = userRepository.findByUsername(principal.getName());
		Company company = companyService.findByUser(user);

		companyService.saveOldData(company);

		String profile = company.getFirstName() + ".jpg";
		String uploadDir = "C:/Company/" + company.getCompanyName() + "/Owner_Profile";

		company.setProfile(profile);
		companyService.saveFile(uploadDir, profile, file);

		companyService.editCompany(company);

		return "redirect:/company/profile?profileUpdated";
	}

	@PostMapping("/change-password")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, Principal principal, Model model) {

		String userName = principal.getName();
		User currentUser = this.userRepository.findByUsername(userName);

		if (this.passwordEncoder.matches(oldPassword, currentUser.getPassword())) {

			currentUser.setPassword(this.passwordEncoder.encode(newPassword));
			this.userRepository.save(currentUser);

			return "redirect:/company/profile?passwordChanged";

		} else {

			return "redirect:/company/profile?wrongPassword";
		}

	}

	@GetMapping("/deleteCompany/{cid}")
	public String deleteCompany(@PathVariable("cid") Long companyId) {

		Company company = companyService.findByCompanyId(companyId);
		User user = company.getUser();

		companyService.deleteCompany(company.getCompanyId());
		userService.deleteByUserId(user.getId());

		return "redirect:/login?companyDeleted";

	}

}
