package com.ingroinfo.ubm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.ubm.entity.Master_Product;
import com.ingroinfo.ubm.helper.ExcelHelper;
import com.ingroinfo.ubm.service.ExcelMasterProductService;

@RestController
@RequestMapping("/product")
public class Master_Product_Controller {

	@Autowired
	private ExcelMasterProductService excelMasterProductService;

	@PostMapping("/Master_Product/upload")
	public ResponseEntity<?> upload(@RequestParam(name = "file", required = true) MultipartFile file) {
		if (ExcelHelper.checkExcelFormat(file)) {

			this.excelMasterProductService.save(file);

			return ResponseEntity.ok(Map.of("message", "File Uploaded and Save in Database"));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel file Only");
	}

	@GetMapping("/Master_Product")
	public List<Master_Product> getAllProduct() {

		return this.excelMasterProductService.getAllProducts();
	}

}
