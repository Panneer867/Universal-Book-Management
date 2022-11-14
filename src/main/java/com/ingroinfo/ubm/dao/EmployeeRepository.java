package com.ingroinfo.ubm.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmployeeId(Long id);

	Employee findByCompanyId(Long id);

	Employee findByMobile(String mobile);

	Employee findByEmail(String email);

	@Transactional
	@Modifying
	@Query("delete from Employee e where e.companyId=:id")
	void deleteByCompanyId(Long id);

}
