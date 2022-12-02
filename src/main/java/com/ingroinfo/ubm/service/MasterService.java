package com.ingroinfo.ubm.service;

import java.util.List;

import com.ingroinfo.ubm.dto.SupplierDto;
import com.ingroinfo.ubm.entity.Brand;
import com.ingroinfo.ubm.entity.BrandPublisher;
import com.ingroinfo.ubm.entity.Category;
import com.ingroinfo.ubm.entity.HsnCode;
import com.ingroinfo.ubm.entity.Item;
import com.ingroinfo.ubm.entity.Supplier;
import com.ingroinfo.ubm.entity.UnitOfMeasures;

public interface MasterService {

	UnitOfMeasures findByUnitId(Long unitId);

	List<UnitOfMeasures> getAllUnits();

	void saveUnitOfMeasure(String unitOfMeasure, String remarks);

	boolean unitOfMeasureCheck(Long brandId, String brandName);

	boolean unitExists(String unitOfMeasure);

	void updateUnitOfMeasure(UnitOfMeasures unit);

	void deleteByUnitId(Long unitId);

	boolean brandExists(String brandName);

	void saveBrand(Brand brand);

	List<Brand> getAllBrands();

	Brand findByBrandId(Long id);

	void updateBrand(Brand brand);

	void fileRename(String brandName, String companyName, Brand brand);

	void deleteByBrandId(Long brandId);

	boolean brandNameCheck(Long brandId, String brandName);

	boolean categoryExists(String category);

	void saveCategory(Category category);

	List<Category> getAllCategories();

	Category findByCategoryId(Long categoryId);

	boolean categoryNameCheck(Long categoryId, String categoryName);

	void updateCategory(Category category);

	void deleteByCategoryId(Long brandId);

	boolean hsnExists(Long hsnCode);

	void saveHsnCode(HsnCode hsnCode);

	List<HsnCode> getAllHsnCode();

	HsnCode findByHsnId(Long hsnId);

	boolean hsnCodeCheck(Long hsnId, Long hsnCode);

	boolean categoryExistsOnHsn(Category category);

	void updateHsnCode(HsnCode hsn);

	void deleteByHsnId(Long hsnId);

	Brand findByBrandName(String brandName);

	boolean reciptExists(String reciptNo);

	boolean gstNoExists(String gstin);

	void saveSupplier(Supplier supplier);

	List<Supplier> getAllSuppliers();

	Supplier findBySupplierId(Long supplierId);

	void updateSupplier(Supplier supplier);

	boolean emailCheck(SupplierDto supplierDto);

	boolean contactNoCheck(SupplierDto supplierDto);

	void deleteBySupplierId(Long supplierId);

	void saveBrandPublisher(BrandPublisher brandPublisher);

	List<BrandPublisher> getAllBrandPublishers();

	BrandPublisher findByPublisherId(Long publisherId);

	void updateBrandPublisher(BrandPublisher brandPublisher);

	void deleteByPublisherId(Long publisherId);

	void saveItem(Item item);

	Category findByCategoryName(String categoryName);

	HsnCode findByHsn(Category category);

	List<Item> getAllItems();

	Item findByItemId(Long itemId);

	void itemRename(String itemName, String companyName, Item item);

	void updateItem(Item item);

	Supplier findBySupplierName(String supplierName);

}
