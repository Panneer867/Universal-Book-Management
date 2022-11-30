package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.dao.CategoryRepository;
import com.ingroinfo.ubm.dao.CompanyRepository;
import com.ingroinfo.ubm.dao.EmployeeRepository;
import com.ingroinfo.ubm.dao.UserRepository;
import com.ingroinfo.ubm.dto.CompanyDto;
import com.ingroinfo.ubm.entity.Company;
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

	@Autowired
	public CategoryRepository categoryRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment environment;

	@GetMapping("/login")
	public String login(Model model) {

		if (companyService.companyExists()) {
			model.addAttribute("enable", null);

		} else {

			model.addAttribute("enable", "enable");
		}

		return "/main/login";
	}

	@GetMapping("/logout")
	public String logout() {

		return "/main/login";
	}

	@GetMapping("/denied")
	public String error() {

		return "/main/access_denied";
	}

	@GetMapping("/error")
	public String serverError() {

		return "/main/server_error";
	}

	@GetMapping("/reset/password")
	public String forgotPassword() {

		return "/main/reset_password";
	}

	@PostMapping("/reset/password")
	public String sendToken(@RequestParam String email, HttpServletRequest request)
			throws MailException, MessagingException {

		User user = userService.findUserByEmail(email);

		if (user != null) {

			String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(user, token);
			mailSender.send(constructResetTokenEmail(token, user, request));

			return "redirect:/login?checkEmailConfirm";
		}
		return "redirect:/reset/password?wrongEmail";
	}

	private MimeMessage constructResetTokenEmail(String token, User user, HttpServletRequest request)
			throws MessagingException {

		String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
				+ "/reset/changePassword?token=" + token;

		return constructEmail(url, user);
	}

	private MimeMessage constructEmail(String url, User user) throws MessagingException {

		String subject = "[UBM] Please reset your password";

		String content = "<h3>Reset your Login password</h3>"
				+ "<p>You can use the following button to reset your password:-</p>" + "<head>"
				+ "<style type=\"text/css\">" + "  .btn-css { appearance: none;\r\n"
				+ "  background-color: #2ea44f;\r\n" + "  border: 1px solid rgba(27, 31, 35, .15);\r\n"
				+ "  border-radius: 6px;\r\n" + "  box-shadow: rgba(27, 31, 35, .1) 0 1px 0;\r\n"
				+ "  box-sizing: border-box;\r\n" + "  color: #fff;\r\n" + "  cursor: pointer;\r\n"
				+ "  display: inline-block;\r\n"
				+ "  font-family: -apple-system,system-ui,\"Segoe UI\",Helvetica,Arial,sans-serif,\"Apple Color Emoji\",\"Segoe UI Emoji\";\r\n"
				+ "  font-size: 14px;\r\n" + "  font-weight: 600;\r\n" + "  line-height: 20px;\r\n"
				+ "  padding: 6px 16px;\r\n" + "  position: relative;\r\n" + "  text-align: center;\r\n"
				+ "  text-decoration: none;\r\n" + "  user-select: none;\r\n" + "  -webkit-user-select: none;\r\n"
				+ "  touch-action: manipulation;\r\n" + "  vertical-align: middle;\r\n" + "  white-space: nowrap; }"
				+ ".text-muted{\r\n" + "opacity: 0.5;\r\n" + "}" + "</style>" + "</head>" + "<a target='_blank' href="
				+ url + "><button class=\"btn-css\">Reset your password</button></a>" + "<br>" + "<br>" + "Thanks,"
				+ "<br>" + "The Ingroinfo Team" + "<br>"
				+ "<p  class=\"text-muted\">You're receiving this email because a password reset was requested for your account.</p>";

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
		helper.setSubject(subject);
		mimeMessage.setContent(content, "text/html");
		helper.setTo(user.getEmail());
		helper.setFrom(environment.getProperty("support.email"));

		return mimeMessage;
	}

	@GetMapping("/reset/changePassword")
	public String changePassword(@RequestParam("token") String token) {
		String result = userService.validatePasswordResetToken(token);

		if (result != null) {

			return "redirect:/login?somethingWentWrong";

		} else {

			return "redirect:/reset/updatePassword?token=" + token;
		}
	}

	@GetMapping("/reset/updatePassword")
	public String updatePasswordPage(@RequestParam("token") String token, Model model) {

		model.addAttribute("token", token);

		return "/main/change_password";
	}

	@PostMapping("/reset/updatePassword")
	public String updatePassword(@RequestParam("token") String token, @RequestParam("password") String newPassword) {

		String result = userService.validatePasswordResetToken(token);

		if (result != null) {

			return "redirect:/login?somethingWentWrong";
		}

		Optional<User> user = userService.getUserByPasswordResetToken(token);
		if (user.isPresent()) {

			userService.changeUserPassword(user.get(), newPassword);
		}
		return "redirect:/login?passwordChanged";
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
		return "/main/company_creation";
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
		String uploadDir1 = "C:/Company/" + company.getCompanyName() + "/Profile";

		company.setCompanyPath("C:/Company/" + company.getCompanyName() + "/");
		company.setCompanyLogo(companyLogo);
		company.setProfile(profile);

		companyService.saveFile(uploadDir, companyLogo, file1);
		companyService.saveFile(uploadDir1, profile, file2);

		companyService.userDetails(companyDto);
		companyService.saveCompany(company);

		return "redirect:/company/register?success";
	}

}
