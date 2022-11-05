package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.ubm.dto.BrandMasterDto;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.BrandMasterService;

@Controller
@RequestMapping("/master")
public class BrandMasterController {

	@Autowired
	private BrandMasterService brandMasterService;

	@GetMapping("/brand-master")
	public String brandMaster(Model model) {
		model.addAttribute("title", "Brand Master");
		model.addAttribute("show", null);
		return "/pages/brand_master";
	}

	@PostMapping("/createBrandMaster")
	public String createBrandMaster(@RequestParam("brandLogo") MultipartFile file1,
			@ModelAttribute("brand") BrandMasterDto brandMasterDto, BindingResult bindingResult, HttpSession session,
			Model model) throws IOException {

		String brandLogo = StringUtils.cleanPath(file1.getOriginalFilename());
		String uploadDir = "C:/Company/Brand_Logo";
		brandMasterDto.setBrandLogo(brandLogo);
		brandMasterDto.setLogoDir(uploadDir);
		brandMasterService.saveFile(uploadDir, brandLogo, file1);
		brandMasterService.saveBrand(brandMasterDto);
		model.addAttribute("title", "Brand Master");
		session.setAttribute("message", new Message("Brand Master Successfully Created !!", "success"));
		return "redirect:/master/brand-master";
	}

	@GetMapping("/findBrandHistory")
	public String findAllBrandData(Model model) {
		List<BrandMasterDto> listOfBrand = brandMasterService.getAllRecord();
		model.addAttribute("listOfBrands", listOfBrand);
		model.addAttribute("show", "show");
		model.addAttribute("title", "Brand Master");
		return "/pages/brand_master";
	}

	@RequestMapping("/deleteBrandData/{brandId}")
	public String deleteBrand(@PathVariable(name = "brandId") Long brandId, HttpSession session, Model model) {
		brandMasterService.deleteBrand(brandId);
		model.addAttribute("title", "Brand Master");
		session.setAttribute("message", new Message("Brand Master Data Successfully Deleted !!", "danger"));
		return "redirect:/master/brand-master";
	}

}
