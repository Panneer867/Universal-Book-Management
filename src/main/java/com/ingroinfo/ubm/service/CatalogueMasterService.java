package com.ingroinfo.ubm.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.dto.CatalogueMasterDto;

public interface CatalogueMasterService {


	CatalogueMasterDto saveCatalogue(CatalogueMasterDto catalogueMasterDto);
	
	List<CatalogueMasterDto> getAllCatalogueData();
	
	void deleteCatalogue(Long catalogueId);
	
	void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;

}
