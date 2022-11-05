package com.ingroinfo.ubm.service;

import com.ingroinfo.ubm.entity.CategoryMaster;

public interface CategoryMasterService {

	void saveCategory(CategoryMaster categoryMaster);

	String dateFormat(String date);

}
