package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.dto.UserDto;
import com.ingroinfo.ubm.entity.Bank;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.State;
import com.ingroinfo.ubm.entity.User;

public interface UserService {

	List<Object[]> getAllStates();

	List<Object[]> getCitiesByState(Integer id);

	List<Bank> getAllBanks();

	void registerCompany(User user);

	void registerBranch(User user);

	void registerUser(User user);

	void registerEmployee(User user);

	boolean userNameExists(String username);

	User getUserId(String username);

	State findById(String name);

	String dateFormat(String date);

	void saveBranchId(Branch branch);

	boolean emailExists(UserDto userDto);

	boolean mobileExists(UserDto userDto);

	boolean usernameExists(UserDto userDto);

	void saveUser(UserDto userDto);

	void editUser(User user);

	void deleteByUserId(Long id);

	boolean usernameChanged(User user);

	List<UserDto> getAllUsers();
}
