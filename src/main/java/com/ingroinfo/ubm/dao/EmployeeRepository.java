package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByEmail(String username);

	Employee findTopByOrderByEmployeeIdDesc();

	Employee findByFirstName(String username);

	Employee findByEmployeeId(Long id);

}
