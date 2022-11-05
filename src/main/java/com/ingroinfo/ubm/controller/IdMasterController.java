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
import com.ingroinfo.ubm.dto.Id_MasterDTO;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.Id_MasterService;

@Controller
@RequestMapping("/master")
public class IdMasterController {

	@Autowired
	private Id_MasterService id_MastreService;

	@GetMapping("/id-Master")
	public String idMaster(Model model) {

		model.addAttribute("title", "Id Master");
		model.addAttribute("show", null);
		return "/pages/id_Master";
	}

	@PostMapping("/createIdMaster")
	public String createIdMaster(@ModelAttribute("id_Master") Id_MasterDTO id_MasterDTO, HttpSession session) {
		id_MastreService.createIdMaster(id_MasterDTO);
		session.setAttribute("message", new Message("Successfuly Upadted in Id Master DataBase", "success"));
		return "redirect:/master/id-Master?success";
	}

	@GetMapping("/showIdData")
	public String IdMasterData(Model model) {
		List<Id_MasterDTO> listOfId = id_MastreService.getAllIdMaster();
		model.addAttribute("listOfId", listOfId);
		model.addAttribute("show", "show");
		return "/pages/id_Master";
	}

	@RequestMapping("/delete/{allMasterid}")
	public String deleteallMaster(@PathVariable(name = "allMasterid") Integer allMasterid) {
		id_MastreService.deleteallMaster(allMasterid);
		return "redirect:/master/id-Master?success";
	}

}
