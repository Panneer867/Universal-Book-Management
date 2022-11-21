package com.ingroinfo.ubm.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Brand;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.UnitOfMeasures;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.MasterService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/master")
public class MasterController {

	@Autowired
	public CompanyService companyService;

	@Autowired
	public BranchService branchService;

	@Autowired
	public UserService userService;

	@Autowired
	public MasterService masterService;

	@GetMapping("/test")
	public String test(Model model, Principal principal) {

		return "/masters/test_page";
	}

	@GetMapping("/barcode")
	public String barcode(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/barcode";
	}

	@GetMapping("/brand")
	public String brand(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		List<Brand> brandList = masterService.getBrands();
		if (brandList.size() == 0) {
			model.addAttribute("emptyList", "No Records");
		}

		model.addAttribute("brands", brandList);

		return "/masters/brand";
	}

	@PostMapping("/brand/add")
	public String brandAdd(@RequestParam("brandName") String brandName, @RequestParam("brandLogo") MultipartFile file,
			Model model, Principal principal) throws IOException {

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);

		Brand brand = new Brand();

		if (masterService.brandExists(brandName)) {
			return "redirect:/master/brand?brandAlreadyExists";
		}

		if (company != null) {

			String fileName = brandName + ".png";
			String uploadDir = "C:/Company/" + company.getCompanyName() + "/Brands";
			brand.setBrandLogo(fileName);
			brand.setBrandName(brandName);
			companyService.saveFile(uploadDir, fileName, file);
			masterService.saveBrand(brand);
		}

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
			return "redirect:/master/brand?brandAlreadyExists";
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
		return "redirect:/master/brand?brandUpdated";
	}

	@GetMapping("/brand/delete")
	public String deleteBrand(@RequestParam Long brandId) {

		masterService.deleteByBrandId(brandId);
		return "redirect:/master/brand?unitDeleted";
	}

	@GetMapping("/bundle")
	public String bundle(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/bundle";
	}

	@GetMapping("/cash-counter")
	public String cashCounter(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/cash_counter";
	}

	@GetMapping("/catalogue")
	public String catalogue(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/catalouge";
	}

	@GetMapping("/category")
	public String category(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/category";
	}

	@GetMapping("/hsn")
	public String hsn(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/hsn";
	}

	@GetMapping("/item")
	public String item(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/item";
	}

	@GetMapping("/publisher")
	public String publishers(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/publishers";
	}

	@GetMapping("/school")
	public String school(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/school";
	}

	@GetMapping("/supplier")
	public String supplier(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/supplier";
	}

	@GetMapping("/tax")
	public String tax(Model model, Principal principal) {
		model.addAttribute("title", "Category Master");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		return "/masters/tax";
	}

	@GetMapping("/measures")
	public String unitOfMeasures(Model model, Principal principal) {
		model.addAttribute("title", "Unit of Measures");

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);
		Branch branch = branchService.findByUserId(user);

		if (company != null) {

			model.addAttribute("pe", company.getProfile());
			model.addAttribute("cne", company.getCompanyName());
			model.addAttribute("companyProfile", "enableCompany");

		} else if (branch != null) {

			Company cmpy = branch.getCompany();
			model.addAttribute("usernameofbranch", branch.getFirstName());
			model.addAttribute("pe", cmpy.getProfile());
			model.addAttribute("cne", cmpy.getCompanyName());
			model.addAttribute("branchProfile", "enableBranch");
		}

		List<UnitOfMeasures> unitList = masterService.getUnits();
		if (unitList.size() == 0) {
			model.addAttribute("emptyList", "No Records");
		}

		model.addAttribute("units", unitList);
		return "/masters/unit_measures";
	}

	@PostMapping("/measures/add")
	public String unitOfMeasuresAdd(@RequestParam String unitOfMeasure, Principal principal) {

		User user = userService.getUserId(principal.getName());
		Company company = companyService.findByUser(user);

		if (masterService.unitExists(unitOfMeasure)) {
			return "redirect:/master/brand?brandAlreadyExists";
		}

		if (company != null) {
			masterService.saveUnitOfMeasure(unitOfMeasure);
		}
		return "redirect:/master/measures?unitAdded";
	}

	@PostMapping("/measures/update")
	public String unitUpdate(@RequestParam String unitId, @RequestParam String unitOfMeasure) {

		UnitOfMeasures unit = masterService.findByUnitId(Long.parseLong(unitId));

		if (masterService.unitOfMeasureCheck(Long.parseLong(unitId), unitOfMeasure)) {
			return "redirect:/master/measures?unitAlreadyExists";
		}

		unit.setUnitOfMeasure(unitOfMeasure);
		masterService.updateUnitOfMeasure(unit);

		return "redirect:/master/measures?unitUpdated";
	}

	@GetMapping("/measures/delete")
	public String deleteEmpmloyee(@RequestParam Long unitId) {

		masterService.deleteByUnitId(unitId);
		return "redirect:/master/measures?unitDeleted";
	}
}
