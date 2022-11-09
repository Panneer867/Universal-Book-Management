package com.ingroinfo.ubm.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByUser(User id);

	Company findTopByOrderByCompanyIdDesc();

	Company findByCompanyId(Long id);

	@Transactional
	void deleteByCompanyId(Long id);
}
