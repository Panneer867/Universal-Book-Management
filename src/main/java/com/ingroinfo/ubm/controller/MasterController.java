package com.ingroinfo.ubm.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ingroinfo.ubm.entity.Branch;
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
	
	/*==================================================================================== */
	/*==================================== Barcode ======================================= */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ Brand Master ====================================== */	
	/*==================================================================================== */

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

		return "/masters/brand";
	}
	
	/*==================================================================================== */
	/*================================ Bundle Creation =================================== */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ Cash Counter ====================================== */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ Catalogue Master ================================== */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ Category Master =================================== */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ HSN Code ========================================== */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ Item Master ======================================= */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ Brand Publisher =================================== */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ School Master ===================================== */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ Supplier Master =================================== */	
	/*==================================================================================== */

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
	
	/*==================================================================================== */
	/*================================ Tax Master ======================================== */	
	/*==================================================================================== */

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

	/*=================================================================================== */
	/*================================ Unit of Measures ================================= */	
	/*==================================================================================== */
	
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

		model.addAttribute("unit", unitList);
		return "/masters/unit_measures";
	}

	@PostMapping("/measures")
	public String unitOfMeasuresAdd(@RequestParam String unitOfMeasure) {

		masterService.saveUnitOfMeasure(unitOfMeasure);
		return "redirect:/master/measures?unitAdded";
	}

	@PostMapping("/measures/update")
	public String unitUpdate(@RequestParam String unitId, @RequestParam String unitOfMeasure) {

		UnitOfMeasures unit = masterService.findByUnitId(Long.parseLong(unitId));
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
