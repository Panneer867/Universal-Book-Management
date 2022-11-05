package com.ingroinfo.ubm.service;


import java.util.List;
import com.ingroinfo.ubm.dto.SchoolMasterDTO;


public interface SchoolMasterService {
	
	//create
	void createSchoolMaster(SchoolMasterDTO schoolMasterDto);
		
	//getAll
	List<SchoolMasterDTO> getSchoolMaster();
	
	//delete
	void deleteSchool(Integer schoolid);
}
