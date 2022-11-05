package com.ingroinfo.ubm.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.ubm.dto.BrandMasterDto;

public interface BrandMasterService {

	void saveBrand(BrandMasterDto brandMasterDto);

	void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;
	
	List<BrandMasterDto> getAllRecord();
	
	void deleteBrand(Long brandId);

}
