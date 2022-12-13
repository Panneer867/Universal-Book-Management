package com.ingroinfo.ubm.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

		if (bundleDto != null) {
			Item item = itemRepository.findByItemName(bundleDto.getItemName());
			if (bundledItemRepository.findByItemId(item.getItemId()) == null) {
				TempBundle tempBundle = modelMapper.map(bundleDto, TempBundle.class);
				tempBundle.setItemId(item.getItemId());
				tempBundle.setQuantity((long) 1);
				bundledItemRepository.save(tempBundle);
				bundleDto.setItemExists("NO");
			} else {
				bundleDto.setItemExists("YES");
			}
		}
		return bundleDto;
	}

	@PostMapping("/bundle/item/id")
	public @ResponseBody void delItem(@RequestBody BundleDto bundleDto) {
		if (bundleDto.getItemName() != null) {
			TempBundle tempBundle = bundledItemRepository.findByItemName(bundleDto.getItemName());
			bundledItemRepository.deleteById(tempBundle.getTempBundleId());
		}
	}
}
