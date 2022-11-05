package com.ingroinfo.ubm.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.dao.Master_Product_items_Repo;
import com.ingroinfo.ubm.entity.Master_Product;
import com.ingroinfo.ubm.helper.ExcelHelper;

@Service
public class ExcelMasterProductService {

	@Autowired
	private Master_Product_items_Repo master_Product_items_Repo;

	public void save(MultipartFile file) {

		List<Master_Product> master_product;
		try {
			master_product = ExcelHelper.convertExcelToListOfProduct(file.getInputStream());
			this.master_Product_items_Repo.saveAll(master_product);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Master_Product> getAllProducts() {
		return this.master_Product_items_Repo.findAll();
	}
}
