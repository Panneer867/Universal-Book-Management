package com.ingroinfo.ubm.service;

import java.util.List;

import com.ingroinfo.ubm.entity.UnitOfMeasures;

public interface MasterService {
	
	List<UnitOfMeasures> getUnits();

	void saveUnitOfMeasure(String unitOfMeasure);
	
	void updateUnitOfMeasure(UnitOfMeasures unit);

	void deleteByUnitId(Long unitId);

	UnitOfMeasures findByUnitId(Long unitId);

}
