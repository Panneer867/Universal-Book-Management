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
import com.ingroinfo.ubm.dto.BundleDTO;
import com.ingroinfo.ubm.dto.ItemMasterDto;
import com.ingroinfo.ubm.dto.SchoolMasterDTO;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.BundleMasterService;
import com.ingroinfo.ubm.service.ItemMasterService;
import com.ingroinfo.ubm.service.SchoolMasterService;

@Controller
@RequestMapping("/master")
public class BundleMasterController {

	@Autowired
	private BundleMasterService bundleMasterService;
	@Autowired
	private SchoolMasterService schoolMasterService;
	@Autowired
	private ItemMasterService itemMasterService;

	@GetMapping("/bundle-create")
	public String bundleCreate(Model model) {
		model.addAttribute("title", "Bundle Create");
		model.addAttribute("show", null);
		List<SchoolMasterDTO> schoolMaster = schoolMasterService.getSchoolMaster();
		model.addAttribute("schoolMaster", schoolMaster);

		List<ItemMasterDto> searchItem = itemMasterService.getAllRecord();
		model.addAttribute("searchItem", searchItem);
		return "/pages/bundle_create";
	}

	@PostMapping("/createBundleMaster")
	public String createBundleMaster(@ModelAttribute("bundle_create") BundleDTO bundleDTO, HttpSession session,
			Model model) {

		bundleMasterService.createBundleMaster(bundleDTO);
		session.setAttribute("message", new Message("Successfuly Upadted in Bundle Master Created", "success"));

		return "redirect:/master/bundle-create?success";
	}

	@GetMapping("/showBundleMasterData")
	public String allBundleMaster(Model model) {
		List<BundleDTO> bundleMaster = bundleMasterService.getAllBundeList();
		model.addAttribute("bundleMaster", bundleMaster);
		model.addAttribute("show", "show");
		return "/pages/bundle_create";
	}

	@RequestMapping("/deleteBundle/{brandId}")
	public String deleteUnits(@PathVariable(name = "brandId") Integer brandId) {
		bundleMasterService.deletebundle(brandId);
		return "redirect:/master/bundle-create?success";
	}

	/*
	 * //search
	 * 
	 * @RequestMapping("/") public String viewHomePage(Model
	 * model, @Param("keyword") String keyword) { List<BundleDTO> listSearchBundle =
	 * bundleMasterService.searchBundles(keyword);
	 * model.addAttribute("listSearchBundle", listSearchBundle);
	 * model.addAttribute("keyword", keyword);
	 * 
	 * return "redirect:/master/bundle-create?success"; }
	 */

}
