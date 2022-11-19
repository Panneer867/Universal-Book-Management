package com.ingroinfo.ubm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.ubm.dao.UnitsRepository;
import com.ingroinfo.ubm.entity.UnitOfMeasures;
import com.ingroinfo.ubm.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private UnitsRepository unitsRepository;

	@Override
	public List<UnitOfMeasures> getUnits() {

		return unitsRepository.findAll();
	}

	@Override
	public void saveUnitOfMeasure(String unitOfMeasure) {
		UnitOfMeasures unit = new UnitOfMeasures();
		unit.setUnitOfMeasure(unitOfMeasure);
		unitsRepository.save(unit);
	}

	@Override
	public void deleteByUnitId(Long unitId) {
		unitsRepository.deleteById(unitId);
		
	}

	@Override
	public UnitOfMeasures findByUnitId(Long unitId) {
		
		return unitsRepository.findByUnitId(unitId);
	}

	@Override
	public void updateUnitOfMeasure(UnitOfMeasures unit) {
		unitsRepository.save(unit);
		
	}

}
