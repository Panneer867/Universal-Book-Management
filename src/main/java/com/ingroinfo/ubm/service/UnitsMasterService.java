package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.dto.UnitsMasterDTO;

public interface UnitsMasterService {

	// create
	void createUnitsMaster(UnitsMasterDTO unitsMasterDTO);

	// getAll
	List<UnitsMasterDTO> getAllUnitsMaster();

	// delete
	void deleteUnits(Integer unitid);

}
