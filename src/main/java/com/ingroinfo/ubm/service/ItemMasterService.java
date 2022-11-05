package com.ingroinfo.ubm.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.dto.ItemMasterDto;

public interface ItemMasterService {

	void saveItem(ItemMasterDto itemDto);

	void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;
	
	List<ItemMasterDto> getAllRecord();
	
	void deleteItem(Integer itemId);
	

	/* Optional<ItemMasterDto> findByBookName(String BookName); */
	
}
