package com.ingroinfo.ubm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.ubm.dto.ItemMasterDto;
import com.ingroinfo.ubm.helper.Message;
import com.ingroinfo.ubm.service.ItemMasterService;

@Controller
@RequestMapping("/master")
public class ItemMasterController {

	@Autowired
	private ItemMasterService itemService;

	@GetMapping("/item-master")
	public String itemMaster(Model model) {
		model.addAttribute("title", "Item Master");
		model.addAttribute("show", null);
		return "/pages/item_master";
	}

	@PostMapping("/saveItemData")
	public String createItemMaster(@RequestParam("itemImage") MultipartFile file2,
			@ModelAttribute("item") ItemMasterDto itemDto, BindingResult bindingResult, HttpSession session)
			throws IOException {

		String itemImage = StringUtils.cleanPath(file2.getOriginalFilename());
		String uploadDir = "C:/Company/Item_Image";
		itemDto.setItemImage(itemImage);
		itemDto.setImageDir(uploadDir);
		itemService.saveFile(uploadDir, itemImage, file2);
		session.setAttribute("message", new Message("Brand Master Successfully Created !!", "success"));
		itemService.saveItem(itemDto);
		return "redirect:/master/item-master";
	}

	@GetMapping("/findItemHistory")
	public String findAllItemData(Model model) {
		List<ItemMasterDto> listOfItem = itemService.getAllRecord();
		model.addAttribute("listOfItems", listOfItem);
		model.addAttribute("show", "show");
		return "/pages/item_master";
	}

	@RequestMapping("/deleteItemData/{itemId}")
	public String deleteBrand(@PathVariable(name = "itemId") Integer itemId, HttpSession session) {
		itemService.deleteItem(itemId);
		session.setAttribute("message", new Message("Item Master Data Successfully Deleted !!", "danger"));
		return "redirect:/master/item-master";
	}

}
