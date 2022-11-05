package com.ingroinfo.ubm.service;

import java.util.List;

import com.ingroinfo.ubm.dto.BranchDto;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;

public interface BranchService {

	void saveBranch(Branch branch);

	List<Branch> getAllBranches();

	Branch findByCompanyId(Company company);

	Branch findByBranchId();

	boolean branchAllowed(Company company);

	void userDetails(BranchDto branchDto);

	boolean emailExists(BranchDto branchDto);

	boolean mobileExists(BranchDto branchDto);

	boolean usernameExists(BranchDto branchDto);
	
	List<BranchDto> getAllBranch();

	Branch findByBranchId(Long branchId);

	void deleteByBranchId(Long branchId);

	Branch findByUserId(User user);
}
