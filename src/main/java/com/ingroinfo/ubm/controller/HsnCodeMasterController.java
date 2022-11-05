package com.ingroinfo.ubm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.ubm.dto.HsnCodeMasterDto;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.HsnCodeMasterService;

@Controller
@RequestMapping("/master")
public class HsnCodeMasterController {

	@Autowired
	private HsnCodeMasterService hsnCodeService;

	@GetMapping("/hsn-code")
	public String hsnCode(Model model) {
		model.addAttribute("title", "HSN Code");
		model.addAttribute("show", null);
		return "/pages/hsn_code";
	}

	@PostMapping("/saveHsnData")
	public String saveHsnCodeData(HsnCodeMasterDto hsnCodeDto, Model model, HttpSession session) {

		hsnCodeService.saveHsnCode(hsnCodeDto);
		model.addAttribute("titel", "HSN Code");
		session.setAttribute("message", new Message("Hsn code Data Saved Successfully !!", "success"));

		return "redirect:/master/hsn-code";
	}

	@GetMapping("/displayHsnData")
	public String displayHsnCodeData(Model model) {

		List<HsnCodeMasterDto> listOfHsnCodeData = hsnCodeService.getAllHsnList();
		model.addAttribute("listOfHsnCodes", listOfHsnCodeData);
		model.addAttribute("show", "show");
		return "/pages/hsn_code";

	}

	@RequestMapping("/deleteHsnCodeData/{hsnId}")
	public String deleteHsnCodeMasterData(@PathVariable(name = "hsnId") Long hsnId, Model model, HttpSession session) {

		hsnCodeService.deleteHsnData(hsnId);
		model.addAttribute("titel", "HSN Code");
		session.setAttribute("message", new Message("Hsn Master Data Successfully Deleted !!", "danger"));

		return "redirect:/master/hsn-code";
	}
}
