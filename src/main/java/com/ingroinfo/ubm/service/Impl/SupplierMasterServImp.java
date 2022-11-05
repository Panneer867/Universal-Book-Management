package com.ingroinfo.ubm.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.dao.SupplierMasterRepo;
import com.ingroinfo.ubm.dto.SupplierMasterDTO;
import com.ingroinfo.ubm.entity.SupplierMaster;
import com.ingroinfo.ubm.service.SupplierMasterService;

@Service
public class SupplierMasterServImp implements SupplierMasterService {

	@Autowired
	private SupplierMasterRepo supplierMasterRepo;

	@Autowired
	private ModelMapper modelMapper;

	// save data

	// getAll data
	@Override
	public List<SupplierMasterDTO> getAllSupplierMasterData() {
		List<SupplierMaster> SupplierMasters = this.supplierMasterRepo.findAll();
		List<SupplierMasterDTO> SupplierMasterDtos = SupplierMasters.stream()
				.map((supplier) -> this.modelMapper.map(supplier, SupplierMasterDTO.class))
				.collect(Collectors.toList());
		return SupplierMasterDtos;
	}

	// delete data

	// upload Image
	@Override
	public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public void deleteSupplier(Integer supplierId) { // TODO
	 * Auto-generated method stub supplierMasterRepo.deleteById(supplierId); }
	 */

	@Override
	public SupplierMasterDTO saveSupplierMasterData(SupplierMasterDTO supplierMasterDTO) {

		SupplierMaster supplierMaster = this.modelMapper.map(supplierMasterDTO, SupplierMaster.class);
		SupplierMaster savedSupplierMaster = supplierMasterRepo.save(supplierMaster);
		return this.modelMapper.map(savedSupplierMaster, SupplierMasterDTO.class);
	}

	@Override
	public void deleteSupplier(Integer supplierId) {
		// TODO Auto-generated method stub
		supplierMasterRepo.deleteById(supplierId);
	}

}
