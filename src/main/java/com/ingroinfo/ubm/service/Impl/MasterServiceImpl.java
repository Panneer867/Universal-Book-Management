package com.ingroinfo.ubm.service.Impl;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.BrandPublisherRepository;
import com.ingroinfo.ubm.dao.BrandRepository;
import com.ingroinfo.ubm.dao.BundleItemRepository;
import com.ingroinfo.ubm.dao.BundleRepository;
import com.ingroinfo.ubm.dao.TempBundleRepository;
import com.ingroinfo.ubm.dao.CategoryRepository;
import com.ingroinfo.ubm.dao.HsnCodeRepository;
import com.ingroinfo.ubm.dao.ItemRepository;
import com.ingroinfo.ubm.dao.SchoolRepository;
import com.ingroinfo.ubm.dao.SupplierRepository;
import com.ingroinfo.ubm.dao.UnitsRepository;
import com.ingroinfo.ubm.dto.ItemDto;
import com.ingroinfo.ubm.dto.SchoolDto;
import com.ingroinfo.ubm.dto.SupplierDto;
import com.ingroinfo.ubm.entity.Brand;
import com.ingroinfo.ubm.entity.BrandPublisher;
import com.ingroinfo.ubm.entity.Bundle;
import com.ingroinfo.ubm.entity.BundleItem;
import com.ingroinfo.ubm.entity.TempBundleItem;
import com.ingroinfo.ubm.entity.Category;
import com.ingroinfo.ubm.entity.HsnCode;
import com.ingroinfo.ubm.entity.Item;
import com.ingroinfo.ubm.entity.School;
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

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private TempBundleRepository tempBundleRepository;

	@Autowired
	private BundleRepository bundleRepository;

	@Autowired
	private BundleItemRepository bundleItemRepository;

	@Override
	public List<UnitOfMeasures> getAllUnits() {
		return unitsRepository.findAll();
	}

	@Override
	public UnitOfMeasures findByUnitId(Long unitId) {
		return unitsRepository.findByUnitId(unitId);
	}

	@Override
	public void saveUnitOfMeasure(String unitOfMeasure, String description) {
		UnitOfMeasures unit = new UnitOfMeasures();
		unit.setUnitOfMeasure(unitOfMeasure);
		unit.setDescription(description);
		unitsRepository.save(unit);
	}

	@Override
	public boolean unitExists(String unitOfMeasure) {
		return unitsRepository.findByUnitOfMeasure(unitOfMeasure) != null;
	}

	@Override
	public boolean unitOfMeasureCheck(Long unitId, String unitOfMeasure) {

		List<UnitOfMeasures> filteredList = unitsRepository.findAll().stream()
				.filter(x -> !unitId.equals(x.getUnitId())).collect(Collectors.toList());

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

		List<Brand> filteredList = brandRepository.findAll().stream().filter(x -> !brandId.equals(x.getBrandId()))
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

		List<Category> filteredList = categoryRepository.findAll().stream()
				.filter(x -> !categoryId.equals(x.getCategoryId())).collect(Collectors.toList());

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

		List<HsnCode> filteredList = hsnCodeRepository.findAll().stream().filter(x -> !hsnId.equals(x.getHsnId()))
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

		List<Supplier> filteredList = supplierRepository.findAll().stream()
				.filter(x -> !supplierDto.getSupplierId().equals(x.getSupplierId())).collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getEmail().equals(supplierDto.getEmail())).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public boolean contactNoCheck(SupplierDto supplierDto) {

		List<Supplier> filteredList = supplierRepository.findAll().stream()
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
	public void itemRename(String itemName, String companyName, Item item, String fileName) {
		boolean isThere = fileName.equals(item.getItemImage());
		if (!isThere) {
			File folder = new File("C:\\Company\\" + companyName + "\\Items\\" + item.getItemImage());
			folder.renameTo(new File("C:\\Company\\" + companyName + "\\Items\\" + fileName));
		}
	}

	@Override
	public void updateItem(Item item) {
		itemRepository.save(item);
	}

	@Override
	public void deleteByItemId(Long itemId) {
		itemRepository.deleteById(itemId);
	}

	@Override
	public BrandPublisher findByPublisherName(String publisherName) {
		return brandPublisherRepository.findByPublisherName(publisherName);
	}

	@Override
	public boolean schoolNameExists(String schoolName) {
		return schoolRepository.findBySchoolName(schoolName) != null;
	}

	@Override
	public boolean schoolEmailExists(String email) {
		return schoolRepository.findByEmail(email) != null;
	}

	@Override
	public void saveSchool(School school) {
		schoolRepository.save(school);
	}

	@Override
	public List<School> getAllSchools() {
		return schoolRepository.findAll();
	}

	@Override
	public boolean schoolNameCheck(SchoolDto schoolDto) {

		List<School> filteredList = schoolRepository.findAll().stream()
				.filter(x -> !schoolDto.getSchoolId().equals(x.getSchoolId())).collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getSchoolName().equals(schoolDto.getSchoolName()))
				.findFirst().isPresent();
		return isExists;
	}

	@Override
	public boolean schoolEmailCheck(SchoolDto schoolDto) {

		List<School> filteredList = schoolRepository.findAll().stream()
				.filter(x -> !schoolDto.getSchoolId().equals(x.getSchoolId())).collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getEmail().equals(schoolDto.getEmail())).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public School findBySchoolId(Long schoolId) {
		return schoolRepository.findBySchoolId(schoolId);
	}

	@Override
	public void updateSchool(School school) {
		schoolRepository.save(school);
	}

	@Override
	public void deleteBySchoolId(Long schoolId) {
		schoolRepository.deleteById(schoolId);
	}

	@Override
	public Long findByCategoryIdOfHsnCodeId(Long categoryId) {

		Category category = findByCategoryId(categoryId);
		HsnCode hsnCode = hsnCodeRepository.findByCategory(category);
		return hsnCode.getHsnId();
	}

	@Override
	public String getByBrandId(Long brandId) {

		String brandName = "NA";
		Brand brand = brandRepository.findByBrandId(brandId);
		if (brand != null) {
			brandName = brand.getBrandName();
		}
		return brandName;
	}

	@Override
	public String getByCategoryId(Long categoryId) {

		String categoryName = "NA";
		Category category = categoryRepository.findByCategoryId(categoryId);
		if (category != null) {
			categoryName = category.getCategoryName();
		}
		return categoryName;
	}

	@Override
	public Long getByHsnCodeId(Long hsnCodeId) {

		Long hsnCode = null;
		HsnCode hsn = hsnCodeRepository.findByHsnId(hsnCodeId);
		if (hsn != null) {
			hsnCode = hsn.getHsnCode();
		}
		return hsnCode;
	}

	@Override
	public String getBySupplierId(Long supplierId) {

		String supplierName = "NA";
		Supplier supplier = supplierRepository.findBySupplierId(supplierId);
		if (supplier != null) {
			supplierName = supplier.getSupplierName();
		}
		return supplierName;
	}

	@Override
	public String getByPublisherId(Long publisherId) {

		String publisherName = "NA";
		BrandPublisher publisher = brandPublisherRepository.findByPublisherId(publisherId);
		if (publisher != null) {
			publisherName = publisher.getPublisherName();
		}
		return publisherName;
	}

	@Override
	public Long getByBrandName(String brandName) {
		Brand brand = brandRepository.findByBrandName(brandName);
		return brand.getBrandId();
	}

	@Override
	public Long getByCategoryName(String categoryName) {
		Category category = categoryRepository.findByCategoryName(categoryName);
		return category.getCategoryId();
	}

	@Override
	public Long getBySupplierName(String supplierName) {
		Supplier supplier = supplierRepository.findBySupplierName(supplierName);
		return supplier.getSupplierId();
	}

	@Override
	public Long getByPublisherName(String publisherName) {
		BrandPublisher publisher = brandPublisherRepository.findByPublisherName(publisherName);
		return publisher.getPublisherId();
	}

	@Override
	public void deleteSupplierIdOnItem(Long supplierId) {
		itemRepository.deleteSupplierId(null, supplierId);

	}

	@Override
	public void deleteBrandIdOnItem(Long brandId) {
		itemRepository.deleteBrandId(null, brandId);
	}

	@Override
	public void deleteCategoryIdOnItem(Long categoryId) {
		itemRepository.deleteCategoryId(null, categoryId);
	}

	@Override
	public void deleteHsnCodeIdOnItem(Long hsnId) {
		itemRepository.deleteHsnCodeId(null, hsnId);
	}

	@Override
	public void deleteBrandPublisherIdOnItem(Long publisherId) {
		itemRepository.deletePublisherId(null, publisherId);
	}

	@Override
	public List<ItemDto> getItemList() {

		List<ItemDto> itemLists = getAllItems().stream().map(temp -> {
			ItemDto obj = new ItemDto();
			obj.setBrandName(getByBrandId(temp.getBrandId()));
			obj.setCategoryName(getByCategoryId(temp.getCategoryId()));
			obj.setDateCreated(temp.getDateCreated());
			obj.setHsnCode(getByHsnCodeId(temp.getHsnCodeId()));
			obj.setItemId(temp.getItemId());
			obj.setItemImage(temp.getItemImage());
			obj.setItemName(temp.getItemName());
			obj.setMrpPrice(temp.getMrpPrice());
			obj.setCostPrice(temp.getCostPrice());
			obj.setSellingPrice(temp.getSellingPrice());
			obj.setItemStatus(temp.getItemStatus());
			obj.setLastUpdated(temp.getLastUpdated());
			obj.setDescription(temp.getDescription());
			obj.setSupplierName(getBySupplierId(temp.getSupplierId()));
			obj.setPublisherName(getByPublisherId(temp.getPublisherId()));
			obj.setUnitOfMeasure(temp.getUnitOfMeasure());
			return obj;
		}).collect(Collectors.toList());
		return itemLists;
	}

	@Override
	public List<TempBundleItem> getAllBundledItems() {
		return tempBundleRepository.findAll();
	}

	@Override
	public void saveBundle(Bundle bundle) {

		bundleRepository.save(bundle);
		Bundle bundleData = bundleRepository.findByBundleName(bundle.getBundleName());
		List<TempBundleItem> allData = tempBundleRepository.findAll();
		for (TempBundleItem h : allData) {
			BundleItem supplier = new BundleItem();
			supplier.setBundleId(bundleData.getBundleId());
			supplier.setItemId(h.getItemId());
			supplier.setItemMrp(h.getItemMrp());
			supplier.setItemName(h.getItemName());
			supplier.setQuantity(h.getQuantity());
			supplier.setSlNo(h.getSlNo());
			supplier.setTotalCost(h.getTotalCost());
			bundleItemRepository.save(supplier);
		}
		tempBundleRepository.deleteAll();
	}

	@Override
	public boolean bundleNameExists(String bundleName) {
		return bundleRepository.findByBundleName(bundleName) != null;
	}

	@Override
	public List<Bundle> getAllBundles() {
		return bundleRepository.findAll();

	}

	@Override
	public List<BundleItem> getAllBundleItems() {
		return bundleItemRepository.findAll();
	}

	@Override
	public Bundle findByBundleId(Long id) {
		return bundleRepository.findByBundleId(id);
	}

	@Override
	public void deleteByBundleId(Long bundleId) {
		bundleItemRepository.deleteByBundleId(bundleId);
		bundleRepository.deleteById(bundleId);
	}
}
