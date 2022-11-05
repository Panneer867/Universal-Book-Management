package com.ingroinfo.ubm.service.Impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.CategoryMasterRepository;
import com.ingroinfo.ubm.entity.CategoryMaster;
import com.ingroinfo.ubm.service.CategoryMasterService;

@Service
public class CategoryMasterServiceImpl implements CategoryMasterService {

	@Autowired
	private CategoryMasterRepository castegoryRepository;

	@Override
	public void saveCategory(CategoryMaster categoryMaster) {
		castegoryRepository.save(categoryMaster);

	}

	@Override
	public String dateFormat(String dbDate) {

		String dt = "";
		String twelveHr = "";
		try {
			Timestamp ts = Timestamp.valueOf(dbDate);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			dt = formatter.format(ts);
			String date[] = dt.split(" ");
			String time = date[1];
			String result = LocalTime.parse(time).format(DateTimeFormatter.ofPattern("h:mm a"));
			twelveHr = date[0] + " " + result;

		} catch (Exception e) {
			System.out.println("Exception Occurs Here :" + e);
		}
		return twelveHr;
	}

	public void deleteCategory(Long categoryId) {

		castegoryRepository.deleteById(categoryId);
	}

}
