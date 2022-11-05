package com.ingroinfo.ubm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingroinfo.ubm.entity.State;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.UserService;

@Controller
public class LoginController {

	@Autowired
	public CompanyService companyService;

	@Autowired
	public UserService userService;

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

}
