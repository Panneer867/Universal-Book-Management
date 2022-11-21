package com.ingroinfo.ubm.service;

import java.util.List;
import java.util.Optional;
import com.ingroinfo.ubm.dto.UserDto;
import com.ingroinfo.ubm.entity.Bank;
import com.ingroinfo.ubm.entity.State;
import com.ingroinfo.ubm.entity.User;

public interface UserService {

	List<Object[]> getAllStates();

	List<Object[]> getCitiesByState(Integer id);

	List<Bank> getAllBanks();

	void registerCompany(User user);

	void registerBranch(User user);

	void registerUser(User user);

	boolean userNameExists(String username);

	User getUserId(String username);

	State findById(String name);

	String dateFormat(String date);

	boolean emailExists(UserDto userDto);

	boolean mobileExists(UserDto userDto);

	boolean usernameExists(UserDto userDto);

	void saveUser(UserDto userDto);

	void editUser(User user);

	void deleteByUserId(Long id);

	List<UserDto> getAllUsers();

	boolean emailCheck(User user);

	boolean mobileCheck(User user);

	User findUserByEmail(String userEmail);

	String validatePasswordResetToken(String token);

	void createPasswordResetTokenForUser(User user, String token);

	Optional<User> getUserByPasswordResetToken(String token);

	void changeUserPassword(User user, String newPassword);
}
