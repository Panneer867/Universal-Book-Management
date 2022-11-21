package com.ingroinfo.ubm.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.BranchRepository;
import com.ingroinfo.ubm.dao.UserRepository;
import com.ingroinfo.ubm.dto.BranchDto;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.BranchService;
import com.ingroinfo.ubm.service.UserService;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserService userService;

	@Override
	public void saveBranch(Branch branch) {

		branchRepository.save(branch);
	}

	@Override
	public List<Branch> getAllBranches() {

		return branchRepository.findAll();
	}

	@Override
	public Branch findByCompany(Company company) {

		return branchRepository.findByCompany(company);
	}

	@Override
	public Branch findByBranchId() {

		return branchRepository.findTopByOrderByBranchIdDesc();
	}

	@Override
	public boolean branchAllowed(Company company) {

		boolean checkBranch = false;

		String branchCount = company.getNoOfBranch();
		List<Branch> branches = branchRepository.findAll();
		int count = Integer.parseInt(branchCount);
		int branchSize = branches.size();
		if (count > branchSize) {
			checkBranch = true;
		}

		return checkBranch;
	}

	@Override
	public void userDetails(User user) {

		user.setUserType("BRANCH ADMIN");

		Branch branch = branchRepository.findByEmail(user.getEmail());
		user.setBranchId(branch.getBranchId());

		userService.registerBranch(user);
		saveId(user);
	}

	@Override
	public boolean emailExists(BranchDto branchDto) {

		return userRepository.findByEmail(branchDto.getEmail()) != null;
	}

	@Override
	public boolean mobileExists(BranchDto branchDto) {

		return userRepository.findByMobile(branchDto.getMobile()) != null;
	}

	@Override
	public boolean usernameExists(BranchDto branchDto) {

		return userRepository.findByUsername(branchDto.getUsername()) != null;
	}

	@Override
	public List<BranchDto> getAllBranch() {

		List<Branch> branches = branchRepository.findAll();

		List<BranchDto> branchDto = branches.stream().map((branch) -> {
			BranchDto newBranch = new BranchDto();
			newBranch.setAddress(branch.getAddress());
			newBranch.setBranchName(branch.getBranchName());
			newBranch.setCity(branch.getCity());
			newBranch.setState(branch.getState());
			newBranch.setPinCode(branch.getPinCode());
			newBranch.setDateCreated(userService.dateFormat(branch.getDateCreated().toString()));
			newBranch.setDesignation(branch.getDesignation());
			newBranch.setEmail(branch.getEmail());
			newBranch.setFirstName(branch.getFirstName());
			newBranch.setLastUpdated(userService.dateFormat(branch.getLastUpdated().toString()));
			newBranch.setMobile(branch.getMobile());
			newBranch.setBranchId(branch.getBranchId());
			return newBranch;
		}).collect(Collectors.toList());

		return branchDto;
	}

	@Override
	public Branch findByBranchId(Long branchId) {

		return branchRepository.findByBranchId(branchId);
	}

	@Override
	public void deleteByBranchId(Long branchId) {

		branchRepository.deleteById(branchId);

	}

	@Override
	public Branch findByUserId(User user) {

		return branchRepository.findByUser(user);
	}

	@Override
	public void updateBranch(Branch branch) {

		branchRepository.save(branch);

	}

	@Override
	public Optional<Branch> getBranchById(Long id) {

		return branchRepository.findById(id);
	}

	public void saveId(User user) {

		User userId = userRepository.findByEmail(user.getEmail());
		Branch branch = branchRepository.findByEmail(user.getEmail());
		branch.setUser(userId);
		branchRepository.save(branch);

	}

	@Override
	public void editBranch(Branch branch) {

		branchRepository.save(branch);
	}

}
