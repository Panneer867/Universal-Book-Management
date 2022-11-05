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
import com.ingroinfo.ubm.dto.SupplierMasterDTO;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.SupplierMasterService;

@Controller
@RequestMapping("/master")
public class SupplierMasterController {

	@Autowired
	private SupplierMasterService supplierMasterService;

	@GetMapping("/supplier-master")
	public String supplierMaster(Model model) {
		model.addAttribute("title", "Supplier Master");
		model.addAttribute("show", null);
		return "/pages/supplier_master";
	}

	@PostMapping("/createSupplierMaster")
	public String saveSupplierMasterData(SupplierMasterDTO supplierMasterDTO, Model model, HttpSession session) {

		model.addAttribute("titel", "Catalogue Master");
		session.setAttribute("message", new Message("Supplier Master Created Successfully !!", "success"));
		supplierMasterService.saveSupplierMasterData(supplierMasterDTO);
		return "redirect:/master/supplier-master";
	}

	@GetMapping("/showSupplierData")
	public String SupplierData(Model model) {
		List<SupplierMasterDTO> supplierlMaster = supplierMasterService.getAllSupplierMasterData();
		model.addAttribute("supplierMaster", supplierlMaster);
		model.addAttribute("show", "show");
		return "/pages/supplier_master";
	}

	@RequestMapping("/deleteSupplier/{supplierId}")
	public String deleteSupplier(@PathVariable(name = "supplierId") Integer supplierId) {
		supplierMasterService.deleteSupplier(supplierId);
		return "redirect:/master/supplier-master";
	}

}
