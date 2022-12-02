package com.ingroinfo.ubm.service.Impl;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingroinfo.ubm.dao.BrandPublisherRepository;
import com.ingroinfo.ubm.dao.BrandRepository;
import com.ingroinfo.ubm.dao.CategoryRepository;
import com.ingroinfo.ubm.dao.HsnCodeRepository;
import com.ingroinfo.ubm.dao.ItemRepository;
import com.ingroinfo.ubm.dao.SupplierRepository;
import com.ingroinfo.ubm.dao.UnitsRepository;
import com.ingroinfo.ubm.dto.SupplierDto;
import com.ingroinfo.ubm.entity.Brand;
import com.ingroinfo.ubm.entity.BrandPublisher;
import com.ingroinfo.ubm.entity.Category;
import com.ingroinfo.ubm.entity.HsnCode;
import com.ingroinfo.ubm.entity.Item;
import com.ingroinfo.ubm.entity.Supplier;
import com.ingroinfo.ubm.entity.UnitOfMeasures;
import com.ingroinfo.ubm.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private UnitsRepository unitsRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private HsnCodeRepository hsnCodeRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private BrandPublisherRepository brandPublisherRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<UnitOfMeasures> getAllUnits() {

		return unitsRepository.findAll();
	}

	@Override
	public UnitOfMeasures findByUnitId(Long unitId) {

		return unitsRepository.findByUnitId(unitId);
	}

	@Override
	public void saveUnitOfMeasure(String unitOfMeasure, String remarks) {

		UnitOfMeasures unit = new UnitOfMeasures();
		unit.setUnitOfMeasure(unitOfMeasure);
		unit.setRemarks(remarks);

		unitsRepository.save(unit);
	}

	@Override
	public boolean unitExists(String unitOfMeasure) {

		return unitsRepository.findByUnitOfMeasure(unitOfMeasure) != null;
	}

	@Override
	public boolean unitOfMeasureCheck(Long unitId, String unitOfMeasure) {

		List<UnitOfMeasures> unitList = unitsRepository.findAll();

		List<UnitOfMeasures> filteredList = unitList.stream().filter(x -> !unitId.equals(x.getUnitId()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getUnitOfMeasure().equals(unitOfMeasure)).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public void updateUnitOfMeasure(UnitOfMeasures unit) {

		unitsRepository.save(unit);
	}

	@Override
	public void deleteByUnitId(Long unitId) {

		unitsRepository.deleteById(unitId);
	}

	@Override
	public boolean brandExists(String brandName) {

		return brandRepository.findByBrandName(brandName) != null;
	}

	@Override
	public void saveBrand(Brand brand) {

		brandRepository.save(brand);
	}

	@Override
	public List<Brand> getAllBrands() {

		return brandRepository.findAll();
	}

	@Override
	public Brand findByBrandId(Long brandId) {

		return brandRepository.findByBrandId(brandId);
	}

	@Override
	public void updateBrand(Brand brand) {

		brandRepository.save(brand);
	}

	@Override
	public void fileRename(String brandName, String companyName, Brand brand) {

		boolean isThere = brandName + ".png" == brand.getBrandLogo();

		if (!isThere) {
			File folder = new File("C:\\Company\\" + companyName + "\\Brands\\" + brand.getBrandLogo());
			folder.renameTo(new File("C:\\Company\\" + companyName + "\\Brands\\" + brandName + ".png"));
		}
	}

	@Override
	public boolean brandNameCheck(Long brandId, String brandName) {

		List<Brand> brandList = brandRepository.findAll();

		List<Brand> filteredList = brandList.stream().filter(x -> !brandId.equals(x.getBrandId()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getBrandName().equals(brandName)).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public void deleteByBrandId(Long brandId) {

		brandRepository.deleteById(brandId);
	}

	@Override
	public boolean categoryExists(String categoryName) {

		return categoryRepository.findByCategoryName(categoryName) != null;
	}

	@Override
	public void saveCategory(Category category) {

		categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {

		return categoryRepository.findAll();
	}

	@Override
	public Category findByCategoryId(Long categoryId) {

		return categoryRepository.findByCategoryId(categoryId);
	}

	@Override
	public boolean categoryNameCheck(Long categoryId, String categoryName) {

		List<Category> categoryList = categoryRepository.findAll();

		List<Category> filteredList = categoryList.stream().filter(x -> !categoryId.equals(x.getCategoryId()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getCategoryName().equals(categoryName)).findFirst()
				.isPresent();

		return isExists;
	}

	@Override
	public Category findByCategoryName(String categoryName) {

		return categoryRepository.findByCategoryName(categoryName);
	}

	@Override
	public void updateCategory(Category category) {

		categoryRepository.save(category);
	}

	@Override
	public void deleteByCategoryId(Long brandId) {

		categoryRepository.deleteById(brandId);
	}

	@Override
	public boolean hsnExists(Long hsnCode) {

		return hsnCodeRepository.findByHsnCode(hsnCode) != null;
	}

	@Override
	public void saveHsnCode(HsnCode hsnCode) {

		hsnCodeRepository.save(hsnCode);
	}

	@Override
	public boolean categoryExistsOnHsn(Category category) {

		return hsnCodeRepository.findByCategory(category) != null;
	}

	@Override
	public List<HsnCode> getAllHsnCode() {

		return hsnCodeRepository.findAll();
	}

	@Override
	public HsnCode findByHsnId(Long hsnId) {

		return hsnCodeRepository.findByHsnId(hsnId);
	}

	@Override
	public boolean hsnCodeCheck(Long hsnId, Long hsnCode) {

		List<HsnCode> hsnList = hsnCodeRepository.findAll();

		List<HsnCode> filteredList = hsnList.stream().filter(x -> !hsnId.equals(x.getHsnId()))
				.collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getHsnCode().equals(hsnCode)).findFirst().isPresent();
		return isExists;
	}

	@Override
	public HsnCode findByHsn(Category category) {

		return hsnCodeRepository.findByCategory(category);
	}

	@Override
	public void updateHsnCode(HsnCode hsn) {

		hsnCodeRepository.save(hsn);
	}

	@Override
	public void deleteByHsnId(Long hsnId) {

		hsnCodeRepository.deleteById(hsnId);
	}

	@Override
	public Brand findByBrandName(String brandName) {

		return brandRepository.findByBrandName(brandName);
	}

	@Override
	public boolean reciptExists(String reciptNo) {

		return supplierRepository.findByReciptNo(reciptNo) != null;
	}

	@Override
	public boolean gstNoExists(String gstin) {

		return supplierRepository.findByGstin(gstin) != null;
	}

	@Override
	public void saveSupplier(Supplier supplier) {

		supplierRepository.save(supplier);
	}

	@Override
	public List<Supplier> getAllSuppliers() {

		return supplierRepository.findAll();
	}

	@Override
	public Supplier findBySupplierId(Long supplierId) {

		return supplierRepository.findBySupplierId(supplierId);
	}

	@Override
	public Supplier findBySupplierName(String supplierName) {

		return supplierRepository.findBySupplierName(supplierName);
	}

	@Override
	public void updateSupplier(Supplier supplier) {

		supplierRepository.save(supplier);
	}

	@Override
	public void deleteBySupplierId(Long supplierId) {

		supplierRepository.deleteById(supplierId);
	}

	@Override
	public boolean emailCheck(SupplierDto supplierDto) {

		List<Supplier> supplierList = supplierRepository.findAll();

		List<Supplier> filteredList = supplierList.stream()
				.filter(x -> !supplierDto.getSupplierId().equals(x.getSupplierId())).collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getEmail().equals(supplierDto.getEmail())).findFirst()
				.isPresent();

		return isExists;
	}

	@Override
	public boolean contactNoCheck(SupplierDto supplierDto) {

		List<Supplier> supplierList = supplierRepository.findAll();

		List<Supplier> filteredList = supplierList.stream()
				.filter(x -> !supplierDto.getSupplierId().equals(x.getSupplierId())).collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getMobile().equals(supplierDto.getMobile())).findFirst()
				.isPresent();

		return isExists;
	}

	@Override
	public void saveBrandPublisher(BrandPublisher brandPublisher) {

		brandPublisherRepository.save(brandPublisher);
	}

	@Override
	public List<BrandPublisher> getAllBrandPublishers() {

		return brandPublisherRepository.findAll();
	}

	@Override
	public BrandPublisher findByPublisherId(Long publisherId) {

		return brandPublisherRepository.findByPublisherId(publisherId);
	}

	@Override
	public void updateBrandPublisher(BrandPublisher brandPublisher) {

		brandPublisherRepository.save(brandPublisher);
	}

	@Override
	public void deleteByPublisherId(Long publisherId) {

		brandPublisherRepository.deleteById(publisherId);
	}

	@Override
	public void saveItem(Item item) {

		itemRepository.save(item);
	}

	@Override
	public List<Item> getAllItems() {

		return itemRepository.findAll();
	}

	@Override
	public Item findByItemId(Long itemId) {

		return itemRepository.findByItemId(itemId);
	}

	@Override
	public void itemRename(String itemName, String companyName, Item item) {

		boolean isThere = itemName + ".jpg" == item.getItemImage();

		if (!isThere) {
			File folder = new File("C:\\Company\\" + companyName + "\\Items\\" + item.getItemImage());
			folder.renameTo(new File("C:\\Company\\" + companyName + "\\Items\\" + itemName + ".jpg"));
		}
	}

	@Override
	public void updateItem(Item item) {

		itemRepository.save(item);

	}

}
