package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.entity.Brand;
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

}
