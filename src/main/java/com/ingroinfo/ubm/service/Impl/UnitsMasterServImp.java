package com.ingroinfo.ubm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.UnitsMasterRepo;
import com.ingroinfo.ubm.dto.UnitsMasterDTO;
import com.ingroinfo.ubm.entity.UnitsMaster;
import com.ingroinfo.ubm.service.UnitsMasterService;

@Service
public class UnitsMasterServImp implements UnitsMasterService {

	@Autowired
	private UnitsMasterRepo unitsMasterRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override

	// save data
	public void createUnitsMaster(UnitsMasterDTO unitsMasterDTO) {
		// TODO Auto-generated method stub

		UnitsMaster unitsMaster = new UnitsMaster();
		unitsMaster.setUnitid(unitsMasterDTO.getUnitid());
		unitsMaster.setUnits_type(unitsMasterDTO.getUnits_type());
		unitsMasterRepo.save(unitsMaster);
	}

	// get data
	@Override
	public List<UnitsMasterDTO> getAllUnitsMaster() {
		// TODO Auto-generated method stub
		List<UnitsMaster> units_Master = this.unitsMasterRepo.findAll();
		List<UnitsMasterDTO> unitsMasterDtos = units_Master.stream()
				.map((unitsMaster) -> this.modelMapper.map(unitsMaster, UnitsMasterDTO.class))
				.collect(Collectors.toList());

		return unitsMasterDtos;

	}

	// delete
	@Override
	public void deleteUnits(Integer unitid) {
		unitsMasterRepo.deleteById(unitid);

	}

}
