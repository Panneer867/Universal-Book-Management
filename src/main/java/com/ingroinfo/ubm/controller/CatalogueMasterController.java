package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.ubm.dto.CatalogueMasterDto;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.CatalogueMasterService;

@Controller
@RequestMapping("/master")
public class CatalogueMasterController {

	@Autowired
	private CatalogueMasterService cataLogueService;

	@GetMapping("/catalogue-master")
	public String displayCatalogueMaster(Model model) {
		model.addAttribute("title", "Catalogue Master");
		model.addAttribute("show", null);
		return "/pages/catalogue_master";
	}

	@PostMapping("/saveCatalogueData")
	public String saveCatalogueData(CatalogueMasterDto catalogueDto, Model model, HttpSession session)
			throws IOException {

		// String imageNameString = filePath.getOriginalFilename();
		// String uploadDir = "C:/Company/Catalogue_Image";
		// catalogueDto.setImagePath(uploadDir);
		// catalogueDto.setUploadImage(imageNameString);
		// cataLogueService.saveFile(uploadDir, imageNameString, filePath);

		model.addAttribute("titel", "Catalogue Master");
		session.setAttribute("message", new Message("Catalogue Master Created Successfully !!", "success"));
		cataLogueService.saveCatalogue(catalogueDto);
		return "redirect:/master/catalogue-master";
	}

	@GetMapping("/findCatalogeData")
	public String findAllCatalogueData(Model model) {
		List<CatalogueMasterDto> listOfCatalogue = cataLogueService.getAllCatalogueData();
		model.addAttribute("listOfCatalogus", listOfCatalogue);
		model.addAttribute("show", "show");
		return "/pages/catalogue_master";
	}

	@RequestMapping("/deleteCatalogue/{catalogueId}")
	public String deleteCatalogue(@PathVariable(name = "catalogueId") Long catalogueId, Model model,
			HttpSession session) {

		cataLogueService.deleteCatalogue(catalogueId);
		model.addAttribute("titel", "Catelogue Master");
		session.setAttribute("message", new Message("Catalogue Master Data Deleted Successfully !!", "danger"));

		return "redirect:/master/catalogue-master";
	}
}
