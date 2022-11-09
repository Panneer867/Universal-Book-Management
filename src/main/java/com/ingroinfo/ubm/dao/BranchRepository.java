package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

	Branch findTopByOrderByBranchIdDesc();

	Branch findByFirstName(String name);

	Branch findByBranchId(Long id);

	Branch findByCompany(Company company);

	Branch findByUser(User user);

	Branch findByEmail(String email);

}
