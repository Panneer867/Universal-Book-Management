package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.entity.Brand;
import com.ingroinfo.ubm.entity.Category;
import com.ingroinfo.ubm.entity.HsnCode;
import com.ingroinfo.ubm.entity.UnitOfMeasures;

public interface MasterService {

	UnitOfMeasures findByUnitId(Long unitId);

	List<UnitOfMeasures> getUnits();

	void saveUnitOfMeasure(String unitOfMeasure);

	boolean unitOfMeasureCheck(Long brandId, String brandName);

	boolean unitExists(String unitOfMeasure);

	void updateUnitOfMeasure(UnitOfMeasures unit);

	void deleteByUnitId(Long unitId);

	boolean brandExists(String brandName);

	void saveBrand(Brand brand);

	List<Brand> getBrands();

	Brand findByBrandId(Long id);

	void updateBrand(Brand brand);

	void fileRename(String brandName, String companyName, Brand brand);

	void deleteByBrandId(Long brandId);

	boolean brandNameCheck(Long brandId, String brandName);

	boolean categoryExists(String categoryName);

	void saveCategory(Category category);

	List<Category> getCategories();

	Category findByCategoryId(Long categoryId);

	boolean categoryNameCheck(Long categoryId, String categoryName);

	void updateCategory(Category category);

	void deleteByCategoryId(Long brandId);

	boolean hsnExists(Long hsnCode);

	void saveHsnCode(HsnCode hsnCode);

	boolean categoryNameExists(String categoryName);

	List<HsnCode> getHsnList();

	HsnCode findByHsnId(Long hsnId);

	boolean hsnCodeCheck(Long hsnId, Long hsnCode);

	void updateHsnCode(HsnCode hsn);

	void deleteByHsnId(Long hsnId);

}
