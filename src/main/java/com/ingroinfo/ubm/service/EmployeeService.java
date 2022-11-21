package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.dto.EmployeeDto;
import com.ingroinfo.ubm.entity.Employee;

public interface EmployeeService {

	void saveEmployee(Employee employee);

	void deleteByEmployeeId(Long employeeId);

	Employee findByCompanyId(Long id);

	boolean emailCheck(Employee employee);

	boolean mobileCheck(Employee employee);

	boolean emailExists(EmployeeDto employeeDto);

	boolean mobileExists(EmployeeDto employeeDto);

	List<EmployeeDto> getAllEmployees();
}
