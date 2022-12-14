package com.ingroinfo.ubm.controller;

import java.text.DecimalFormat;
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
import com.ingroinfo.ubm.entity.TempBundleItem;
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
		if (bundleDto.getItemId() != null) {
			Item item = itemRepository.findByItemId(bundleDto.getItemId());
			if (bundledItemRepository.findByItemId(item.getItemId()) == null) {
				TempBundleItem tempBundle = modelMapper.map(bundleDto, TempBundleItem.class);
				tempBundle.setItemName(item.getItemName());
				bundledItemRepository.save(tempBundle);
				bundleDto.setItemExists("NO");
			} else {
				bundleDto.setItemExists("YES");
			}
		}
		return bundleDto;
	}

	@PostMapping("/bundle/item/quantity")
	public @ResponseBody void quantity(@RequestBody BundleDto bundleDto) {
		if (bundleDto.getItemId() != null) {
			TempBundleItem tempBundle = bundledItemRepository.findByItemId(bundleDto.getItemId());
			DecimalFormat df = new DecimalFormat("#.##");
			tempBundle.setTotalCost(Double.parseDouble(df.format(bundleDto.getItemMrp() * bundleDto.getQuantity())));
			tempBundle.setQuantity(bundleDto.getQuantity());
			bundledItemRepository.save(tempBundle);
		}
	}

	@PostMapping("/bundle/item/delete")
	public @ResponseBody void delItem(@RequestBody BundleDto bundleDto) {
		if (bundleDto.getItemId() != null) {
			TempBundleItem tempBundle = bundledItemRepository.findByItemId(bundleDto.getItemId());
			bundledItemRepository.deleteById(tempBundle.getRowId());
		}
	}
}
