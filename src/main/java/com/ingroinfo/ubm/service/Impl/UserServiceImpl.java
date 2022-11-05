package com.ingroinfo.ubm.service.Impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.UserRepository;
import com.ingroinfo.ubm.dto.UserDto;
import com.ingroinfo.ubm.dao.BankRepository;
import com.ingroinfo.ubm.dao.BranchRepository;
import com.ingroinfo.ubm.dao.RoleRepository;
import com.ingroinfo.ubm.dao.StateRepository;
import com.ingroinfo.ubm.entity.Bank;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.State;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private BankRepository bankRepository;

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	private void register(User user) {

		user.setPassword(getEncodedPassword(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void registerCompany(User user) {

		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_OWNER")));
		register(user);
	}

	@Override
	public void registerBranch(User user) {

		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_MANAGER")));
		register(user);
	}

	@Override
	public void registerUser(User user) {

		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		register(user);
	}

	@Override
	public void registerEmployee(User user) {

		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_EMPLOYEE")));
		register(user);
	}

	@Override
	public boolean userNameExists(String username) {

		return userRepository.findByUsername(username) != null;
	}

	@Override
	public User getUserId(String username) {

		return userRepository.findByUsername(username);
	}

	public List<Object[]> getAllStates() {
		return stateRepository.getAllStates();
	}

	public List<Object[]> getCitiesByState(Integer id) {
		return stateRepository.getCitiesByState(id);
	}

	@Override
	public List<Bank> getAllBanks() {

		return bankRepository.findAll();
	}

	@Override
	public State findById(String name) {

		State state = stateRepository.findByName(name);

		return state;
	}

	@Override
	public void saveBranchId(Branch branch) {

		User user = userRepository.findByEmail(branch.getEmail());
		if (user != null) {

			user.setBranchId(branch.getBranchId());
			userRepository.save(user);
		}
	}

	@Override
	public void saveUser(UserDto userDto) {

		userDto.getRole();

		long branchId = Long.parseLong(userDto.getBranch());
		Branch branch = branchRepository.findByBranchId(branchId);
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUsername());
		user.setFirstName(userDto.getFirstName());
		user.setMobile(userDto.getMobile());
		user.setBranchId(branch.getBranchId());

		if (userDto.getRole().equalsIgnoreCase("ROLE_USER")) {

			user.setUserType("Normal User");
			user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
			register(user);

		} else if (userDto.getRole().equalsIgnoreCase("ROLE_MANAGER")) {

			user.setUserType("Branch User");
			user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_MANAGER")));
			register(user);
		}

	}

	@Override
	public boolean emailExists(UserDto userDto) {

		return userRepository.findByEmail(userDto.getEmail()) != null;
	}

	@Override
	public boolean mobileExists(UserDto userDto) {

		return userRepository.findByMobile(userDto.getMobile()) != null;
	}

	@Override
	public boolean usernameExists(UserDto userDto) {

		return userRepository.findByUsername(userDto.getUsername()) != null;
	}

	@Override
	public String dateFormat(String dbDate) {

		String dateTime = "";
		String twelveHourFormat = "";

		try {

			Timestamp ts = Timestamp.valueOf(dbDate);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			dateTime = formatter.format(ts);
			String date[] = dateTime.split(" ");
			String time = date[1];
			String result = LocalTime.parse(time).format(DateTimeFormatter.ofPattern("h:mm a"));
			twelveHourFormat = date[0] + " " + result;

		} catch (Exception e) {

			System.out.println("Exception Occurs Date and Time Converting Here :" + e);
		}
		return twelveHourFormat;
	}

	@Override
	public void editUser(User user) {

		Optional<User> userDetails = userRepository.findById(user.getId());

		User userDetail = userDetails.get();

		boolean name = userDetail.getFirstName().equalsIgnoreCase(user.getFirstName());
		boolean username = userDetail.getUsername().equalsIgnoreCase(user.getUsername());
		boolean email = userDetail.getEmail().equalsIgnoreCase(user.getEmail());
		boolean mobile = userDetail.getMobile().equalsIgnoreCase(user.getMobile());

		if (!name || !email || !username || !mobile) {

			userRepository.save(user);
		}
	}

	@Override
	public void deleteByUserId(Long id) {

		userRepository.deleteById(id);
	}

	@Override
	public boolean usernameChanged(User user) {

		boolean changed = false;
		Optional<User> userDetails = userRepository.findById(user.getId());

		User userDetail = userDetails.get();

		boolean username = userDetail.getUsername().equalsIgnoreCase(user.getUsername());

		if (!username) {
			changed = true;
		}

		return changed;
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> users = userRepository.findAll();

		List<UserDto> userDto = users.stream().map((user) -> {
			UserDto newUser = new UserDto();
			newUser.setDateCreated(dateFormat(user.getDateCreated().toString()));
			newUser.setEmail(user.getEmail());
			newUser.setFirstName(user.getFirstName());
			newUser.setLastUpdated(dateFormat(user.getLastUpdated().toString()));
			newUser.setMobile(user.getMobile());
			newUser.setUsername(user.getUsername());
			newUser.setUserType(user.getUserType());
			newUser.setBranchId(user.getBranchId());
			newUser.setId(user.getId());
			return newUser;
		}).collect(Collectors.toList());

		for (Iterator<UserDto> it = userDto.iterator(); it.hasNext();) {
			if (it.next().getUserType().equalsIgnoreCase("Company Owner"))
				it.remove();
		}

		return userDto;
	}

}
