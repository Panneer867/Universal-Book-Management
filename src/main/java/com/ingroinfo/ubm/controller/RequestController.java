package com.ingroinfo.ubm.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.ingroinfo.ubm.dao.TempBundleRepository;
import com.ingroinfo.ubm.dao.ItemRepository;
import com.ingroinfo.ubm.dto.BundleDto;
import com.ingroinfo.ubm.entity.TempBundle;
import com.ingroinfo.ubm.entity.Item;

@RestController
@RequestMapping("/post")
public class RequestController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private TempBundleRepository bundledItemRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostMapping("/bundle/item")
	public @ResponseBody BundleDto postItem(@RequestBody BundleDto bundleDto) {

		System.out.println("The id "+bundleDto.getItemId());
		if (bundleDto.getItemId() != null) {
			
			Item item = itemRepository.findByItemId(bundleDto.getItemId());
			if (bundledItemRepository.findByItemId(item.getItemId()) == null) {
				TempBundle tempBundle = modelMapper.map(bundleDto, TempBundle.class);
				tempBundle.setItemName(item.getItemName());
				bundledItemRepository.save(tempBundle);
				bundleDto.setItemExists("NO");
			} else {
				bundleDto.setItemExists("YES");
			}
		}
		return bundleDto;
	}

	@PostMapping("/bundle/item/id")
	public @ResponseBody void delItem(@RequestParam Long itemId) {
		if (itemId != null) {
			TempBundle tempBundle = bundledItemRepository.findByItemId(itemId);
			bundledItemRepository.deleteById(tempBundle.getTempBundleId());
		}
	}	
	
	@PostMapping("/bundle/item/quantity")
	public @ResponseBody void quantity(@RequestParam Long itemId, @RequestParam Long quantity) {
		if (itemId != null) {
			TempBundle tempBundle = bundledItemRepository.findByItemId(itemId);
			tempBundle.setQuantity(quantity);
			bundledItemRepository.save(tempBundle);
		}
	}
}
