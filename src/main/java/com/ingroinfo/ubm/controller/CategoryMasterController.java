package com.ingroinfo.ubm.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ingroinfo.ubm.dao.CategoryMasterRepository;
import com.ingroinfo.ubm.dto.CategoryMasterDto;
import com.ingroinfo.ubm.entity.CategoryMaster;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.CategoryMasterService;

@Controller
@RequestMapping("/master/category-master")

public class CategoryMasterController {

	@Autowired
	private CategoryMasterService categoryMasterService;

	@Autowired
	CategoryMasterRepository repo;

	@GetMapping
	public String masterAdd(Model model) {
		model.addAttribute("title", "Category Add");
		model.addAttribute("category", new CategoryMaster());

		List<CategoryMaster> listCategoryMaster = repo.findAll();
		List<CategoryMasterDto> masterList = listCategoryMaster.stream().map((master) -> {
			CategoryMasterDto mast = new CategoryMasterDto();
			mast.setCategoryId(master.getCategoryId());
			mast.setCategoryName(master.getCategoryName());
			mast.setCategoryStatus(master.getCategoryStatus());
			mast.setDateCreated(categoryMasterService.dateFormat(master.getDateCreated().toString()));
			mast.setHsnCode(master.getHsnCode());
			return mast;
		}).collect(Collectors.toList());

		if (listCategoryMaster != null) {
			model.addAttribute("listCategory", masterList);
		}
		return "/pages/category_master";
	}

	@DeleteMapping("/delete/{categoryId}")
	public String deleteCategory(@PathVariable(name = "categoryId") Long categoryId) {
		repo.deleteById(categoryId);
		return "redirect:/master/category-master";

	}

//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    private String deleteCategory(@RequestParam String id){
//        System.out.println("Category_Id : "+id);
//        return "redirect:/pages/category_master";
//    }

	@PostMapping
	public String saveCategory(@ModelAttribute("category") CategoryMaster category, BindingResult bindingResult,
			HttpSession session) {
		categoryMasterService.saveCategory(category);
		session.setAttribute("message", new Message("Category Data Successfully Added", "success"));
		System.out.println("save category data =" + category.toString());
		return "redirect:/master/category-master?success";
	}

}
