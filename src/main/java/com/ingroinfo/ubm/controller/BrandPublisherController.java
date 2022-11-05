package com.ingroinfo.ubm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingroinfo.ubm.dto.BrandPublisherDTO;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.BrandPublisherService;

@Controller
@RequestMapping("/master")
public class BrandPublisherController {

	@Autowired
	private BrandPublisherService brandPublisherService;

	@GetMapping("/brand-publishers")
	public String brandCreate(Model model) {
		model.addAttribute("title", "Brand Create");
		model.addAttribute("show", null);
		return "/pages/brand_publishers";
	}

	@PostMapping("/createBrandPublisherMaster")
	public String createBrandPublisherMaster(@ModelAttribute("id_Master") BrandPublisherDTO brandPublisherDTO,
			HttpSession session) {
		brandPublisherService.createBrandPublisherMaster(brandPublisherDTO);
		session.setAttribute("message", new Message("Successfuly Upadted in DataBase", "success"));
		return "redirect:/master/brand-publishers?success";
	}

	@GetMapping("/showBrandPublisherData")
	public String BrandPublisherData(Model model) {
		List<BrandPublisherDTO> listOfBrandPublisher = brandPublisherService.getBrandPublisher();
		model.addAttribute("listOfBrandPublisher", listOfBrandPublisher);
		model.addAttribute("show", "show");
		return "/pages/brand_publishers";
	}

	@RequestMapping("/deleteBundelPublisher/{brandPublisherId}")
	public String deleteUnits(@PathVariable(name = "brandPublisherId") Integer brandPublisherId) {
		brandPublisherService.deleteBrandPublisher(brandPublisherId);
		return "redirect:/master/brand-publishers?success";
	}

}
