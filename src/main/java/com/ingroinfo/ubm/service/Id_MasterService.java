package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.dto.Id_MasterDTO;

public interface Id_MasterService {

	// create
	void createIdMaster(Id_MasterDTO idMasterDto);

	// getAll
	List<Id_MasterDTO> getAllIdMaster();

	// delete
	void deleteallMaster(Integer allMasterid);
}
