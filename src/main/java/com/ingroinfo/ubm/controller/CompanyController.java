package com.ingroinfo.ubm.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
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
import com.ingroinfo.ubm.dto.UserDto;
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

		Company company = companyService.findByUser(userRepository.findByUsername(principal.getName()));

		model.addAttribute("pe", company.getProfile());
		model.addAttribute("cne", company.getCompanyName());
		model.addAttribute("details", company);
		model.addAttribute("username", principal.getName());
		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("bankList", userService.getAllBanks());
		model.addAttribute("update", new CompanyDto());
		model.addAttribute("user", new UserDto());
		model.addAttribute("companyProfile", "enableCompany");
		model.addAttribute("usernameExists", "You have entered an username that already exists!");
		model.addAttribute("emailExists", "You have entered an email that already exists!");
		model.addAttribute("mobileExists", "You have entered an mobile that already exists!");
		model.addAttribute("profile", "Profile has been sucessfully updated !");
		model.addAttribute("changed", "Login Credentials has been sucessfully updated !");
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

		String folder = "C:/Company/" + company.getCompanyName() + "/";

		if (!oldfolder.equalsIgnoreCase(folder)) {
			File rename = new File(folder);
			file.renameTo(rename);
			company.setCompanyPath(folder);
		}

		if (userService.emailCheck(user)) {
			return "redirect:/company/profile?emailAlreadyExists";
		}

		if (userService.mobileCheck(user)) {
			return "redirect:/company/profile?mobileAlreadyExists";
		}

		userService.editUser(user);

		companyService.editCompany(company);

		return "redirect:/company/profile?profileUpdated";
	}

	@PostMapping("/profile/picture")
	public String companyUpdate(@RequestParam("profile") MultipartFile file,
			@ModelAttribute("company") CompanyDto companyDto, BindingResult bindingResult, Principal principal,
			Model model) throws IOException {

		Company company = companyService.findByUser(userRepository.findByUsername(principal.getName()));

		companyService.saveOldData(company);

		String profile = company.getFirstName() + ".jpg";
		String uploadDir = "C:/Company/" + company.getCompanyName() + "/Owner_Profile";

		company.setProfile(profile);
		companyService.saveFile(uploadDir, profile, file);

		companyService.editCompany(company);

		return "redirect:/company/profile?profileUpdated";
	}

	@PostMapping("/userDetails")
	public String userDetails(@ModelAttribute("user") UserDto userDto, Principal principal, Model model) {

		User user = userRepository.findByUsername(principal.getName());

		if (this.passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {

			List<User> userList = userRepository.findAll();

			List<User> filteredList = userList.stream().filter(x -> !user.getEmail().equals(x.getEmail()))
					.collect(Collectors.toList());

			boolean isExists = filteredList.stream().filter(o -> o.getUsername().equals(userDto.getUsername()))
					.findFirst().isPresent();

			if (isExists) {
				return "redirect:/company/profile?usernameExists";
			}

			user.setUsername(userDto.getUsername());

			if (userDto.getNewPassword().equalsIgnoreCase("")) {
				userRepository.save(user);
			} else {
				user.setPassword(this.passwordEncoder.encode(userDto.getNewPassword()));
				userRepository.save(user);
			}

			return "redirect:/login?userDetailsChanged";

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
