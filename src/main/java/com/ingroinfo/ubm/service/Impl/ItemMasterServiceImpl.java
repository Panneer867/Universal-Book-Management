package com.ingroinfo.ubm.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.dao.ItemMasterRepository;
import com.ingroinfo.ubm.dto.ItemMasterDto;
import com.ingroinfo.ubm.entity.ItemMaster;
import com.ingroinfo.ubm.service.ItemMasterService;

@Service
public class ItemMasterServiceImpl implements ItemMasterService {

	@Autowired
	private ItemMasterRepository itemRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public void saveItem(ItemMasterDto itemDto) {
		
		ItemMaster item = new ItemMaster();
		item.setItemId(itemDto.getItemId());
		item.setItemName(itemDto.getItemName());
		item.setItemStatus(itemDto.getItemStatus());
		item.setCreateDate(itemDto.getCreateDate());
		item.setCategoryName(itemDto.getCategoryName());
		item.setAuthorName(itemDto.getAuthorName());
		item.setPublisherName(itemDto.getPublisherName());
		item.setItemImage(itemDto.getItemImage());
		item.setImageDir(itemDto.getImageDir());
		
		itemRepository.save(item);

	}

	@Override
	public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}

	@Override
	public List<ItemMasterDto> getAllRecord() {
		List<ItemMaster> itemMasters = this.itemRepository.findAll();		
	    List<ItemMasterDto> itemMasterDtos =  itemMasters.stream().map((item)->this.modelMapper.map(item,ItemMasterDto.class)).collect(Collectors.toList());	
		return itemMasterDtos;
	}

	@Override
	public void deleteItem(Integer itemId) {
		
		itemRepository.deleteById(itemId);
	}

}
