package com.ingroinfo.ubm.controller;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingroinfo.ubm.dao.BrandPublisherRepository;
import com.ingroinfo.ubm.dao.BrandRepository;
import com.ingroinfo.ubm.dao.CategoryRepository;
import com.ingroinfo.ubm.dao.EmployeeRepository;
import com.ingroinfo.ubm.dao.HsnCodeRepository;
import com.ingroinfo.ubm.dao.SupplierRepository;
import com.ingroinfo.ubm.dao.UnitsRepository;
import com.ingroinfo.ubm.dao.UserRepository;
import com.ingroinfo.ubm.dto.BranchDto;
import com.ingroinfo.ubm.dto.UserDto;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Brand;
import com.ingroinfo.ubm.entity.BrandPublisher;
import com.ingroinfo.ubm.entity.Category;
import com.ingroinfo.ubm.entity.Employee;
import com.ingroinfo.ubm.entity.HsnCode;
import com.ingroinfo.ubm.entity.State;
import com.ingroinfo.ubm.entity.Supplier;
import com.ingroinfo.ubm.entity.UnitOfMeasures;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.UserService;

@Controller
@RequestMapping("/get")
public class ResponseController {

	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public UserService userService;

	@Autowired
	public BranchService branchService;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public CategoryRepository categoryRepository;

	@Autowired
	private HsnCodeRepository hsnCodeRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private UnitsRepository unitsRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private BrandPublisherRepository brandPublisherRepository;

	@GetMapping("/city")
	public @ResponseBody String getCities(@RequestParam String stateName) {

		State state = userService.findById(stateName);
		Integer stateId = state.getId();

		String json = null;
		List<Object[]> list = userService.getCitiesByState(stateId);
		try {
			json = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@ResponseBody
	@RequestMapping("/branch")
	public BranchDto getBranch(@RequestParam Long id) {

		Optional<Branch> branch = branchService.getBranchById(id);

		BranchDto branchDto = modelMapper.map(branch, BranchDto.class);

		branchDto.setUsername(branch.get().getUser().getUsername());

		return branchDto;
	}

	@ResponseBody
	@RequestMapping("/user")
	public UserDto getUser(@RequestParam Long id) {

		User user = userRepository.findByUserId(id);

		String type = user.getUserType();

		if (type.equalsIgnoreCase("NORMAL USER")) {
			user.setUserType("ROLE_USER");
		} else if (type.equalsIgnoreCase("BRANCH USER")) {
			user.setUserType("ROLE_BRANCH");
		}

		return modelMapper.map(user, UserDto.class);
	}

	@ResponseBody
	@RequestMapping("/employee")
	public Employee getEmployee(@RequestParam Long id) {

		return employeeRepository.findByEmployeeId(id);
	}

	@ResponseBody
	@RequestMapping("/unit")
	public UnitOfMeasures getUnit(@RequestParam Long id) {

		return unitsRepository.findByUnitId(id);
	}

	@ResponseBody
	@RequestMapping("/brand")
	public Brand getBrand(@RequestParam Long id) {

		return brandRepository.findByBrandId(id);
	}

	@ResponseBody
	@RequestMapping("/category")
	public Category getCategory(@RequestParam Long id) {

		return categoryRepository.findByCategoryId(id);
	}

	@ResponseBody
	@RequestMapping("/hsn")
	public HsnCode getHsnCode(@RequestParam Long id) {

		return hsnCodeRepository.findByHsnId(id);
	}

	@ResponseBody
	@RequestMapping("/supplier")
	public Supplier getSupplier(@RequestParam Long id) {

		return supplierRepository.findBySupplierId(id);
	}

	@ResponseBody
	@RequestMapping("/publisher")
	public BrandPublisher getPublisher(@RequestParam Long id) {

		return brandPublisherRepository.findByPublisherId(id);
	}

}
