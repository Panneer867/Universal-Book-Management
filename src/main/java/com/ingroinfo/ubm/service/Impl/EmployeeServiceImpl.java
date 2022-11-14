package com.ingroinfo.ubm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.EmployeeRepository;
import com.ingroinfo.ubm.dto.EmployeeDto;
import com.ingroinfo.ubm.entity.Employee;
import com.ingroinfo.ubm.service.EmployeeService;
import com.ingroinfo.ubm.service.UserService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserService userService;

	@Override
	public void saveEmployee(Employee employee) {

		employeeRepository.save(employee);
	}

	@Override
	public void deleteByEmployeeId(Long employeeId) {

		employeeRepository.deleteById(employeeId);
	}

	@Override
	public Employee findByCompanyId(Long id) {

		return employeeRepository.findByCompanyId(id);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {

		List<Employee> employeeList = employeeRepository.findAll();

		List<EmployeeDto> employeeDto = employeeList.stream().map((employee) -> {
			EmployeeDto newEmployee = new EmployeeDto();
			newEmployee.setEmployeeId(employee.getEmployeeId());
			newEmployee.setCompanyId(employee.getCompanyId());
			newEmployee.setFirstName(employee.getFirstName());
			newEmployee.setProfile(employee.getProfile());
			newEmployee.setEmail(employee.getEmail());
			newEmployee.setMobile(employee.getMobile());
			newEmployee.setDateCreated(userService.dateFormat(employee.getDateCreated().toString()));
			newEmployee.setLastUpdated(userService.dateFormat(employee.getLastUpdated().toString()));
			return newEmployee;
		}).collect(Collectors.toList());

		return employeeDto;
	}

	@Override
	public boolean emailCheck(Employee employee) {

		List<Employee> employeeList = employeeRepository.findAll();

		List<Employee> filteredList = employeeList.stream()
				.filter(x -> !employee.getEmployeeId().equals(x.getEmployeeId())).collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getEmail().equals(employee.getEmail())).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public boolean mobileCheck(Employee employee) {

		List<Employee> employeeList = employeeRepository.findAll();

		List<Employee> filteredList = employeeList.stream()
				.filter(x -> !employee.getEmployeeId().equals(x.getEmployeeId())).collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getMobile().equals(employee.getMobile())).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public boolean emailExists(EmployeeDto employeeDto) {

		return employeeRepository.findByEmail(employeeDto.getEmail()) != null;
	}

	@Override
	public boolean mobileExists(EmployeeDto employeeDto) {

		return employeeRepository.findByMobile(employeeDto.getMobile()) != null;
	}

}
