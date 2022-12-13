package com.ingroinfo.ubm.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.configuration.ModelMapperConfig;
import com.ingroinfo.ubm.dao.TempBundleRepository;
import com.ingroinfo.ubm.dto.BrandPublisherDto;
import com.ingroinfo.ubm.dto.BundleDto;
import com.ingroinfo.ubm.dto.HsnDto;
import com.ingroinfo.ubm.dto.ItemDto;
import com.ingroinfo.ubm.dto.SchoolDto;
import com.ingroinfo.ubm.dto.SupplierDto;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Brand;
import com.ingroinfo.ubm.entity.BrandPublisher;
import com.ingroinfo.ubm.entity.TempBundle;
import com.ingroinfo.ubm.entity.Category;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.HsnCode;
import com.ingroinfo.ubm.entity.Item;
import com.ingroinfo.ubm.entity.School;
import com.ingroinfo.ubm.entity.Supplier;
import com.ingroinfo.ubm.entity.UnitOfMeasures;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.MasterService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/master")
public class MasterController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ModelMapperConfig mapper;

	@Autowired
	public CompanyService companyService;

	@Autowired
	public BranchService branchService;

	@Autowired
	public UserService userService;

	@Autowired
	public MasterService masterService;

	@Autowired
	private TempBundleRepository bundledItemRepository;

	@GetMapping("/brand")
	public String brand(Model model, Principal principal) {

		model.addAttribute("title", "Brand Master");
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/brand";
	}

	@GetMapping("/brand/list")
	public String brandList(Model model, Principal principal) {

		model.addAttribute("title", "Brand Lists");
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		List<Brand> brandList = masterService.getAllBrands();
		if (brandList.size() == 0) {
			model.addAttribute("emptyList", "No Records");
		}

		model.addAttribute("brands", brandList);

		return "/masters/brand_list";
	}

	@PostMapping("/brand/add")
	public String brandAdd(@RequestParam("brandName") String brandName, @RequestParam("brandLogo") MultipartFile file,
			Model model, Principal principal) throws IOException {

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (masterService.brandExists(brandName)) {
			return "redirect:/master/brand?brandAlreadyExists";
		}

		Brand brand = new Brand();

		if (company != null) {

			String fileName = brandName + ".png";
			String uploadDir = "C:/Company/" + company.getCompanyName() + "/Brands";
			brand.setBrandLogo(fileName);
			brand.setBrandName(brandName);
			companyService.saveFile(uploadDir, fileName, file);

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			String fileName = brandName + ".png";
			String uploadDir = "C:/Company/" + cmpy.getCompanyName() + "/Brands";
			brand.setBrandLogo(fileName);
			brand.setBrandName(brandName);
			companyService.saveFile(uploadDir, fileName, file);
		}

		masterService.saveBrand(brand);

		return "redirect:/master/brand?brandAdded";
	}

	@PostMapping("/brand/update")
	public String brandUpdate(@RequestParam String brandId, @RequestParam String brandName,
			@RequestParam("brandLogo") MultipartFile file, Principal principal) throws IOException {

		Brand brand = masterService.findByBrandId(Long.parseLong(brandId));
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		String companyName = company.getCompanyName();

		if (masterService.brandNameCheck(Long.parseLong(brandId), brandName)) {
			return "redirect:/master/brand/list?brandAlreadyExists";
		}

		if (file.getOriginalFilename().equals("")) {

			masterService.fileRename(brandName, companyName, brand);

			String fileName = brandName + ".png";
			brand.setBrandLogo(fileName);
			brand.setBrandName(brandName);

		} else {

			File folder = new File("C:\\Company\\" + companyName + "\\Brands\\" + brand.getBrandLogo());

			if (folder.delete()) {

				String fileName = brandName + ".png";
				String uploadDir = "C:/Company/" + companyName + "/Brands";
				brand.setBrandLogo(fileName);
				brand.setBrandName(brandName);
				companyService.saveFile(uploadDir, fileName, file);
			}
		}

		masterService.updateBrand(brand);
		return "redirect:/master/brand/list?brandUpdated";
	}

	@GetMapping("/brand/delete")
	public String deleteBrand(@RequestParam Long brandId) {

		masterService.deleteBrandIdOnItem(brandId);
		masterService.deleteByBrandId(brandId);
		return "redirect:/master/brand/list?brandDeleted";
	}

	@GetMapping("/category")
	public String category(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/category";
	}

	@GetMapping("/category/list")
	public String categoryList(Model model, Principal principal) {

		model.addAttribute("title", "Category Lists");
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		List<Category> categoryList = masterService.getAllCategories();

		if (categoryList.size() == 0) {
			model.addAttribute("emptyList", "No Records");
		}

		model.addAttribute("categories", categoryList);
		return "/masters/category_list";
	}

	@PostMapping("/category/add")
	public String categoryAdd(@RequestParam String categoryName, @RequestParam String categoryStatus) {

		if (masterService.categoryExists(categoryName)) {
			return "redirect:/master/category?categoryAlreadyExists";
		}

		Category category = new Category();
		category.setCategoryName(categoryName);
		category.setCategoryStatus(categoryStatus);

		masterService.saveCategory(category);

		return "redirect:/master/category?categoryAdded";
	}

	@PostMapping("/category/update")
	public String categoryUpdate(@RequestParam String categoryId, @RequestParam String categoryName,
			@RequestParam String categoryStatus, Principal principal) throws IOException {

		Category category = masterService.findByCategoryId(Long.parseLong(categoryId));

		if (masterService.categoryNameCheck(Long.parseLong(categoryId), categoryName)) {
			return "redirect:/master/category/list?categoryAlreadyExists";
		}

		category.setCategoryName(categoryName);
		category.setCategoryStatus(categoryStatus);

		masterService.updateCategory(category);
		return "redirect:/master/category/list?categoryUpdated";
	}

	@GetMapping("/category/delete")
	public String deleteCategory(@RequestParam Long categoryId) {

		masterService.deleteCategoryIdOnItem(categoryId);
		masterService.deleteByCategoryId(categoryId);
		return "redirect:/master/category/list?categoryDeleted";
	}

	@GetMapping("/measures")
	public String unitOfMeasures(Model model, Principal principal) {

		model.addAttribute("title", "Unit of Measures");
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/unit_measures";
	}

	@GetMapping("/measures/list")
	public String unitOfMeasuresList(Model model, Principal principal) {

		model.addAttribute("title", "Unit of Measures List");
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		List<UnitOfMeasures> unitList = masterService.getAllUnits();
		if (unitList.size() == 0) {
			model.addAttribute("emptyList", "No Records");
		}

		model.addAttribute("units", unitList);
		return "/masters/unit_measures_list";
	}

	@PostMapping("/measures/add")
	public String unitOfMeasuresAdd(@RequestParam String unitOfMeasure, @RequestParam String description,
			Principal principal) {

		if (masterService.unitExists(unitOfMeasure)) {
			return "redirect:/master/measures?unitAlreadyExists";
		}
		masterService.saveUnitOfMeasure(unitOfMeasure, description);

		return "redirect:/master/measures?unitAdded";
	}

	@PostMapping("/measures/update")
	public String unitUpdate(@RequestParam String unitId, @RequestParam String unitOfMeasure,
			@RequestParam String description) {

		UnitOfMeasures unit = masterService.findByUnitId(Long.parseLong(unitId));

		if (masterService.unitOfMeasureCheck(Long.parseLong(unitId), unitOfMeasure)) {
			return "redirect:/master/measures?unitAlreadyExists";
		}

		unit.setUnitOfMeasure(unitOfMeasure);
		unit.setDescription(description);
		masterService.updateUnitOfMeasure(unit);

		return "redirect:/master/measures/list?unitUpdated";
	}

	@GetMapping("/measures/delete")
	public String deleteUnit(@RequestParam Long unitId) {

		masterService.deleteByUnitId(unitId);
		return "redirect:/master/measures/list?unitDeleted";
	}

	@GetMapping("/hsn")
	public String hsnCode(Model model, Principal principal) {
		model.addAttribute("title", "HSN Code Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		model.addAttribute("categories", masterService.getAllCategories());
		return "/masters/hsn";
	}

	@GetMapping("/hsn/list")
	public String hsnList(Model model, Principal principal) {

		model.addAttribute("title", "HSN Code Lists");
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		List<HsnCode> hsnList = masterService.getAllHsnCode();
		if (hsnList.size() == 0) {
			model.addAttribute("emptyList", "No Records");
		}

		List<HsnDto> hsnLists = hsnList.stream().map(temp -> {
			HsnDto obj = new HsnDto();
			obj.setHsnId(temp.getHsnId());
			obj.setCategoryName(temp.getCategory().getCategoryName());
			obj.setHsnCode(temp.getHsnCode());
			obj.setDateCreated(temp.getDateCreated());
			obj.setLastUpdated(temp.getLastUpdated());
			return obj;
		}).collect(Collectors.toList());

		model.addAttribute("hsnLists", hsnLists);
		model.addAttribute("categories", masterService.getAllCategories());

		return "/masters/hsn_list";
	}

	@PostMapping("/hsn/add")
	public String hsnAdd(@RequestParam String categoryName, @RequestParam Long hsnCode, Principal principal) {

		if (masterService.hsnExists(hsnCode)) {
			return "redirect:/master/hsn?hsnAlreadyExists";
		}

		if (masterService.categoryExistsOnHsn(masterService.findByCategoryName(categoryName))) {
			return "redirect:/master/hsn?categoryAlreadyExists";
		}

		HsnCode hsn = new HsnCode();
		hsn.setCategory(masterService.findByCategoryName(categoryName));
		hsn.setHsnCode(hsnCode);

		masterService.saveHsnCode(hsn);
		return "redirect:/master/hsn?hsnAdded";
	}

	@PostMapping("/hsn/update")
	public String hsnUpdate(@RequestParam Long hsnId, @RequestParam Long hsnCode) {

		HsnCode hsn = masterService.findByHsnId(hsnId);

		if (masterService.hsnCodeCheck(hsnId, hsnCode)) {
			return "redirect:/master/hsn/list?hsnAlreadyExists";
		}

		hsn.setHsnCode(hsnCode);
		masterService.updateHsnCode(hsn);
		return "redirect:/master/hsn/list?hsnUpdated";
	}

	@GetMapping("/hsn/delete")
	public String deleteHsnCode(@RequestParam Long hsnId) {

		masterService.deleteHsnCodeIdOnItem(hsnId);
		masterService.deleteByHsnId(hsnId);
		return "redirect:/master/hsn/list?hsnDeleted";
	}

	@GetMapping("/supplier")
	public String supplier(Model model, Principal principal) {

		model.addAttribute("title", "Supplier Master");
		model.addAttribute("supplier", new SupplierDto());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
			model.addAttribute("details", company);

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
			model.addAttribute("details", cmpy);
		}

		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("bankList", userService.getAllBanks());
		return "/masters/supplier";
	}

	@PostMapping("/supplier/add")
	public String supplierAdd(@ModelAttribute("supplier") SupplierDto supplierDto, Principal principal) {

		if (masterService.reciptExists(supplierDto.getReciptNo())) {
			return "redirect:/master/supplier?reciptAlreadyExists";
		}

		if (masterService.gstNoExists(supplierDto.getGstin())) {
			return "redirect:/master/supplier?gstNoAlreadyExists";
		}

		Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

		masterService.saveSupplier(supplier);
		return "redirect:/master/supplier?supplierAdded";
	}

	@GetMapping("/supplier/list")
	public String supplierList(Model model, Principal principal) {

		model.addAttribute("title", "Suppliers Lists");
		model.addAttribute("supplier", new SupplierDto());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		List<Supplier> supplierList = masterService.getAllSuppliers();
		if (supplierList.size() == 0) {
			model.addAttribute("emptyList", "No Records");
		}

		model.addAttribute("supplierLists", supplierList);
		model.addAttribute("stateList", userService.getAllStates());
		model.addAttribute("bankList", userService.getAllBanks());

		return "/masters/supplier_list";
	}

	@PostMapping("/supplier/update")
	public String supplierUpdate(@ModelAttribute("supplier") SupplierDto supplierDto) {

		Supplier supplier = masterService.findBySupplierId(supplierDto.getSupplierId());

		if (masterService.contactNoCheck(supplierDto)) {
			return "redirect:/master/supplier/list?contactNoAlreadyExists";
		}

		if (masterService.emailCheck(supplierDto)) {
			return "redirect:/master/supplier/list?emailAlreadyExists";
		}

		mapper.modelMapper().map(supplierDto, supplier);
		masterService.updateSupplier(supplier);

		return "redirect:/master/supplier/list?supplierUpdated";
	}

	@GetMapping("/supplier/delete")
	public String deleteSupplier(@RequestParam Long supplierId) {

		masterService.deleteSupplierIdOnItem(supplierId);
		masterService.deleteBySupplierId(supplierId);
		return "redirect:/master/supplier/list?supplierDeleted";
	}

	@GetMapping("/brand/publisher")
	public String brandPublisher(Model model, Principal principal) {

		model.addAttribute("title", "Brand Publisher");
		model.addAttribute("publisher", new BrandPublisherDto());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
			model.addAttribute("details", company);

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
			model.addAttribute("details", cmpy);
		}

		model.addAttribute("brands", masterService.getAllBrands());
		model.addAttribute("categories", masterService.getAllCategories());
		model.addAttribute("suppliers", masterService.getAllSuppliers());

		return "/masters/brand_publisher";
	}

	@PostMapping("/brand/publisher/add")
	public String brandPublisherAdd(@ModelAttribute("publisher") BrandPublisherDto brandPublisherDto) {

		BrandPublisher brandPublisher = modelMapper.map(brandPublisherDto, BrandPublisher.class);

		masterService.saveBrandPublisher(brandPublisher);
		return "redirect:/master/brand/publisher?brandPublisherAdded";
	}

	@GetMapping("/brand/publisher/list")
	public String brandPublisherList(Model model, Principal principal) {

		model.addAttribute("title", "Brand Publishers Lists");
		model.addAttribute("publisher", new BrandPublisherDto());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		List<BrandPublisher> brandPublisherList = masterService.getAllBrandPublishers();
		if (brandPublisherList.size() == 0) {
			model.addAttribute("emptyList", "No Records");
		}

		model.addAttribute("brandPublisherLists", brandPublisherList);
		model.addAttribute("brands", masterService.getAllBrands());
		model.addAttribute("categories", masterService.getAllCategories());
		model.addAttribute("suppliers", masterService.getAllSuppliers());

		return "/masters/brand_publisher_list";
	}

	@PostMapping("/brand/publisher/update")
	public String brandPublisherUpdate(@ModelAttribute("publisherUpdate") BrandPublisherDto brandPublisherDto) {

		BrandPublisher brandPublisher = masterService.findByPublisherId(brandPublisherDto.getPublisherId());

		mapper.modelMapper().map(brandPublisherDto, brandPublisher);
		masterService.updateBrandPublisher(brandPublisher);

		return "redirect:/master/brand/publisher/list?brandPublisherUpdated";
	}

	@GetMapping("/brand/publisher/delete")
	public String deleteBrandPublisher(@RequestParam Long publisherId) {

		masterService.deleteBrandPublisherIdOnItem(publisherId);
		masterService.deleteByPublisherId(publisherId);
		return "redirect:/master/brand/publisher/list?brandPublisherDeleted";
	}

	@GetMapping("/item")
	public String item(Model model, Principal principal) {

		model.addAttribute("title", "Item Master");
		model.addAttribute("item", new ItemDto());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
			model.addAttribute("details", company);

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
			model.addAttribute("details", cmpy);
		}

		model.addAttribute("brands", masterService.getAllBrands());
		model.addAttribute("categories", masterService.getAllCategories());
		model.addAttribute("suppliers", masterService.getAllSuppliers());
		model.addAttribute("publishers", masterService.getAllBrandPublishers());
		model.addAttribute("units", masterService.getAllUnits());

		return "/masters/item";
	}

	@PostMapping("/item/add")
	public String itemAdd(@RequestParam("itemImage") MultipartFile file, @ModelAttribute("item") ItemDto itemDto,
			BindingResult bindingResult, Principal principal) throws IOException {

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		Item item = modelMapper.map(itemDto, Item.class);
		item.setHsnCodeId(masterService.findByCategoryIdOfHsnCodeId(itemDto.getCategoryId()));

		if (company != null) {

			String fileName = itemDto.getItemName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + ".jpg";
			String uploadDir = "C:/Company/" + company.getCompanyName() + "/Items";
			item.setItemImage(fileName);
			companyService.saveFile(uploadDir, fileName, file);

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			String fileName = itemDto.getItemName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + ".jpg";
			String uploadDir = "C:/Company/" + cmpy.getCompanyName() + "/Items";
			item.setItemImage(fileName);
			companyService.saveFile(uploadDir, fileName, file);
		}

		masterService.saveItem(item);

		return "redirect:/master/item?itemAdded";
	}

	@GetMapping("/item/list")
	public String itemList(Model model, Principal principal) {

		model.addAttribute("title", "Item Lists");
		model.addAttribute("item", new ItemDto());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		model.addAttribute("itemLists", masterService.getItemList());
		model.addAttribute("brands", masterService.getAllBrands());
		model.addAttribute("categories", masterService.getAllCategories());
		model.addAttribute("suppliers", masterService.getAllSuppliers());
		model.addAttribute("publishers", masterService.getAllBrandPublishers());
		model.addAttribute("units", masterService.getAllUnits());

		return "/masters/item_list";
	}

	@PostMapping("/item/update")
	public String ItemUpdate(@ModelAttribute("item") ItemDto itemDto, @RequestParam("itemPic") MultipartFile file,
			Principal principal, BindingResult bindingResult) throws IOException {

		Item item = masterService.findByItemId(itemDto.getItemId());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		String companyName = company.getCompanyName();
		String itemName = itemDto.getItemName();

		if (file.getOriginalFilename().equals("")) {

			String fileName = itemName + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + ".jpg";
			masterService.itemRename(itemDto.getItemName(), companyName, item, fileName);

			item.setItemImage(fileName);
			item.setItemName(itemName);

		} else {

			File folder = new File("C:\\Company\\" + companyName + "\\Items\\" + item.getItemImage());

			if (folder.delete()) {

				String fileName = itemDto.getItemName() + "_" + ThreadLocalRandom.current().nextInt(1, 100000) + ".jpg";
				String uploadDir = "C:/Company/" + companyName + "/Items";
				item.setItemImage(fileName);
				item.setItemName(itemName);
				companyService.saveFile(uploadDir, fileName, file);
			}
		}

		mapper.modelMapper().map(itemDto, item);

		item.setBrandId(masterService.getByBrandName(itemDto.getBrandName()));
		item.setCategoryId(masterService.getByCategoryName(itemDto.getCategoryName()));
		item.setSupplierId(masterService.getBySupplierName(itemDto.getSupplierName()));
		item.setPublisherId(masterService.getByPublisherName(itemDto.getPublisherName()));

		masterService.updateItem(item);
		return "redirect:/master/item/list?itemUpdated";
	}

	@GetMapping("/item/delete")
	public String deleteItem(@RequestParam Long itemId) {

		masterService.deleteByItemId(itemId);

		return "redirect:/master/item/list?itemDeleted";
	}

	@GetMapping("/school")
	public String school(Model model, Principal principal) {

		model.addAttribute("title", "School Master");
		model.addAttribute("school", new SchoolDto());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		model.addAttribute("stateList", userService.getAllStates());
		return "/masters/school";
	}

	@PostMapping("/school/add")
	public String schoolAdd(@ModelAttribute("school") SchoolDto schoolDto, Principal principal) {

		if (masterService.schoolNameExists(schoolDto.getSchoolName())) {
			return "redirect:/master/school?schoolNameAlreadyExists";
		}
		if (masterService.schoolEmailExists(schoolDto.getEmail())) {
			return "redirect:/master/school?schoolEmailAlreadyExists";
		}
		School school = modelMapper.map(schoolDto, School.class);
		masterService.saveSchool(school);
		return "redirect:/master/school?schoolAdded";
	}

	@GetMapping("/school/list")
	public String schoolList(Model model, Principal principal) {

		model.addAttribute("title", "School Lists");
		model.addAttribute("school", new SchoolDto());
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {
			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");
		} else if (branch != null) {
			Company cmpy = branch.getCompany();
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("branchProfile", "enableBranch");
		}
		List<School> schoolList = masterService.getAllSchools();
		model.addAttribute("schoolLists", schoolList);
		model.addAttribute("stateList", userService.getAllStates());
		return "/masters/school_list";
	}

	@PostMapping("/school/update")
	public String schoolUpdate(@ModelAttribute("school") SchoolDto schoolDto) {

		if (masterService.schoolNameCheck(schoolDto)) {
			return "redirect:/master/school/list?schoolNameAlreadyExists";
		}

		if (masterService.schoolEmailCheck(schoolDto)) {
			return "redirect:/master/school/list?schoolEmailAlreadyExists";
		}

		School school = masterService.findBySchoolId(schoolDto.getSchoolId());

		mapper.modelMapper().map(schoolDto, school);
		masterService.updateSchool(school);
		return "redirect:/master/school/list?schoolUpdated";
	}

	@GetMapping("/school/delete")
	public String deleteSchool(@RequestParam Long schoolId) {
		masterService.deleteBySchoolId(schoolId);
		return "redirect:/master/school/list?schoolDeleted";
	}

	@GetMapping("/bundle")
	public String bundle(Model model, Principal principal) {

		model.addAttribute("title", "Bundle Master");
		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);
		model.addAttribute("bundle", new BundleDto());

		if (company != null) {

			model.addAttribute("profileData", company.getProfile());
			model.addAttribute("companyNameData", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("profileData", cmpy.getProfile());
			model.addAttribute("companyNameData", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		List<ItemDto> itemList = masterService.getItemList().stream()
				.filter(x -> x.getCategoryName().equalsIgnoreCase("Books")).collect(Collectors.toList());

		List<TempBundle> bundledItem = masterService.getAllBundledItems();
		if (bundledItem.size() == 0) {
			model.addAttribute("emptyList", "No Records");
		}
		model.addAttribute("schoolLists", masterService.getAllSchools());
		model.addAttribute("itemLists", itemList);
		model.addAttribute("bundledItems", bundledItem);
		return "/masters/bundle";
	}

	@GetMapping("/bundle/clear")
	public String clearBundle() {
		bundledItemRepository.deleteAll();
		return "redirect:/master/bundle";
	}
}