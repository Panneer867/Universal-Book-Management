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
import com.ingroinfo.ubm.dto.TaxMasterDto;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.TaxMasterService;

@Controller
@RequestMapping("/master/tax-master")
public class TaxMasterController {

	@Autowired
	private TaxMasterService taxService;

	@GetMapping
	public String displayTaxMaster(Model model) {
		List<TaxMasterDto> taxData = taxService.getAllPosts();
		model.addAttribute("title", "Tax Master View");
		model.addAttribute("TaxDatas", taxData);
		return "/pages/tax_master";
	}

	@PostMapping("/addTaxData")
	public String saveTaxMasterData(TaxMasterDto taxDto, HttpSession session) {

		taxService.createTaxMasterDto(taxDto);
		session.setAttribute("message", new Message("Tax Master Data Successfully Added", "success"));
		return "redirect:/master/tax-master";
	}

	@RequestMapping("/deleteTaxData/{taxId}")
	public String deleteTaxMasterData(@PathVariable(name = "taxId") Long taxId) {
		taxService.deleteTaxMasterDto(taxId);
		return "redirect:/master/tax-master";

	}

//    @PutMapping("/updateTaxData/{taxId}")
//    public void updateTaxMasterData(@PathVariable(name="taxId")Long taxId) {
//        taxService.updateTaxMasterDto(taxId, taxMasterDto)
//       return
//    }   

}
