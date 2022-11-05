package com.ingroinfo.ubm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.School_MasterRepo;
import com.ingroinfo.ubm.dto.SchoolMasterDTO;
import com.ingroinfo.ubm.entity.SchoolMaster;
import com.ingroinfo.ubm.service.SchoolMasterService;

@Service
public class SchoolMasterServImpl implements SchoolMasterService {

	@Autowired
	private School_MasterRepo schoolMasterRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createSchoolMaster(SchoolMasterDTO schoolMasterDto) {

		SchoolMaster schoolMaster = new SchoolMaster();
		schoolMaster.setSchoolid(schoolMasterDto.getSchoolid());
		schoolMaster.setSchoolCode(schoolMasterDto.getSchoolCode());
		schoolMaster.setSchoolName(schoolMasterDto.getSchoolName());
		schoolMaster.setBoard(schoolMasterDto.getBoard());
		schoolMaster.setSchoolAddress(schoolMasterDto.getSchoolAddress());

		schoolMasterRepo.save(schoolMaster);

	}

	@Override
	public List<SchoolMasterDTO> getSchoolMaster() {
		List<SchoolMaster> school_Master = this.schoolMasterRepo.findAll();
		List<SchoolMasterDTO> schoolMasterDtos = school_Master.stream()
				.map((schoolMaster) -> this.modelMapper.map(schoolMaster, SchoolMasterDTO.class))
				.collect(Collectors.toList());

		return schoolMasterDtos;
	}

	@Override
	public void deleteSchool(Integer schoolid) {
		// TODO Auto-generated method stub
		schoolMasterRepo.deleteById(schoolid);
	}

}
