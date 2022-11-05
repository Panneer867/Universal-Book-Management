package com.ingroinfo.ubm.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.dto.SupplierMasterDTO;

public interface SupplierMasterService {

	SupplierMasterDTO saveSupplierMasterData(SupplierMasterDTO supplierMasterDTO);

	List<SupplierMasterDTO> getAllSupplierMasterData();

	void deleteSupplier(Integer supplierId);

	void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;

}
