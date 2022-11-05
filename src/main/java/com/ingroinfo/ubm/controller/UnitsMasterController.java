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

import com.ingroinfo.ubm.dto.UnitsMasterDTO;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.UnitsMasterService;

@Controller
@RequestMapping("/master")
public class UnitsMasterController {

	@Autowired
	private UnitsMasterService unitsMasterService;

	@GetMapping("/unit-of-measures")
	public String unitMeasures(Model model) {
		model.addAttribute("title", "Unit of Measures");
		model.addAttribute("show", null);
		return "/pages/unit_of_measures";
	}

	@PostMapping("/createUnitsMaster")
	public String createUnitsMaster(@ModelAttribute("unit_of_measures") UnitsMasterDTO unitsMasterDTO,
			HttpSession session) {
		unitsMasterService.createUnitsMaster(unitsMasterDTO);
		session.setAttribute("message", new Message("Successfuly Upadted in Units Master DataBase", "success"));
		return "redirect:/master/unit-of-measures?success";
	}

	@GetMapping("/UnitData")
	public String UnitsAllData(Model model) {
		List<UnitsMasterDTO> listOfUnit = unitsMasterService.getAllUnitsMaster();
		model.addAttribute("listOfUnits", listOfUnit);
		model.addAttribute("show", "show");
		return "/pages/unit_of_measures";
	}

	@RequestMapping("/deleteUnit/{unitid}")
	public String deleteUnits(@PathVariable(name = "unitid") Integer unitid) {
		unitsMasterService.deleteUnits(unitid);
		return "redirect:/master/unit-of-measures?success";
	}

}
