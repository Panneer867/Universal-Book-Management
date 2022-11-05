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
import com.ingroinfo.ubm.dto.SchoolMasterDTO;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.SchoolMasterService;

@Controller
@RequestMapping("/master")
public class SchoolMasterController {

	@Autowired
	private SchoolMasterService schoolMasterService;

	@GetMapping("/school-master")
	public String schoolMaster(Model model) {
		model.addAttribute("tittle", "School Master");
		model.addAttribute("show", null);
		return "/pages/school_master";
	}

	@PostMapping("/createSchoolMaster")
	public String createSchoolMaster(@ModelAttribute("schoolMaster") SchoolMasterDTO schoolMasterDTO,
			HttpSession session) {

		schoolMasterService.createSchoolMaster(schoolMasterDTO);
		session.setAttribute("message", new Message("Successfuly Upadted in School Master DataBase", "success"));
		return "redirect:/master/school-master?success";
	}

	@GetMapping("/showSchoolData")
	public String schoolData(Model model) {
		List<SchoolMasterDTO> schoolMaster = schoolMasterService.getSchoolMaster();
		model.addAttribute("schoolMaster", schoolMaster);
		model.addAttribute("show", "show");
		return "/pages/school_master";
	}

	@RequestMapping("/deleteSchool/{schoolid}")
	public String deleteSchool(@PathVariable(name = "schoolid") Integer schoolid) {
		schoolMasterService.deleteSchool(schoolid);
		return "redirect:/master/school-master?success";
	}

}
