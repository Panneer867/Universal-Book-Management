package com.ingroinfo.ubm.service.Impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.UserRepository;
import com.ingroinfo.ubm.dto.UserDto;
import com.ingroinfo.ubm.dao.BankRepository;
import com.ingroinfo.ubm.dao.BranchRepository;
import com.ingroinfo.ubm.dao.PasswordResetTokenRepository;
import com.ingroinfo.ubm.dao.RoleRepository;
import com.ingroinfo.ubm.dao.StateRepository;
import com.ingroinfo.ubm.entity.Bank;
import com.ingroinfo.ubm.entity.Branch;
import com.ingroinfo.ubm.entity.PasswordResetToken;
import com.ingroinfo.ubm.entity.State;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.service.UserService;

@Service
@Transactional
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

	@Autowired
	private PasswordResetTokenRepository passwordTokenRepository;

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	private void register(User user) {

		user.setPassword(getEncodedPassword(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void registerCompany(User user) {

		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
		register(user);
	}

	@Override
	public void registerBranch(User user) {

		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_BRANCH")));
		register(user);
	}

	@Override
	public void registerUser(User user) {

		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
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
	public void saveUser(UserDto userDto) {

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

			user.setUserType("NORMAL USER");
			user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
			register(user);

		} else if (userDto.getRole().equalsIgnoreCase("ROLE_BRANCH")) {

			user.setUserType("BRANCH USER");
			user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_BRANCH")));
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

		Optional<User> userDetails = userRepository.findById(user.getUserId());
		User userDetail = userDetails.get();
		boolean name = userDetail.getFirstName().equalsIgnoreCase(user.getFirstName());
		boolean email = userDetail.getEmail().equalsIgnoreCase(user.getEmail());
		boolean mobile = userDetail.getMobile().equalsIgnoreCase(user.getMobile());

		if (!name || !email || !mobile) {

			userRepository.save(user);

		}

	}

	@Override
	public void deleteByUserId(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<UserDto> userDto = userRepository.findAll().stream().map((user) -> {
			UserDto newUser = new UserDto();
			newUser.setDateCreated(dateFormat(user.getDateCreated().toString()));
			newUser.setEmail(user.getEmail());
			newUser.setFirstName(user.getFirstName());
			newUser.setLastUpdated(dateFormat(user.getLastUpdated().toString()));
			newUser.setMobile(user.getMobile());
			newUser.setUsername(user.getUsername());
			newUser.setUserType(user.getUserType());
			newUser.setBranchId(user.getBranchId());
			newUser.setUserId(user.getUserId());
			return newUser;
		}).collect(Collectors.toList());

		for (Iterator<UserDto> it = userDto.iterator(); it.hasNext();) {
			if (it.next().getUserType().equalsIgnoreCase("COMPANY OWNER"))
				it.remove();
		}

		return userDto;
	}

	@Override
	public boolean emailCheck(User user) {

		List<User> filteredList = userRepository.findAll().stream()
				.filter(x -> !user.getUsername().equals(x.getUsername())).collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getEmail().equals(user.getEmail())).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public boolean mobileCheck(User user) {

		List<User> filteredList = userRepository.findAll().stream()
				.filter(x -> !user.getUsername().equals(x.getUsername())).collect(Collectors.toList());

		boolean isExists = filteredList.stream().filter(o -> o.getMobile().equals(user.getMobile())).findFirst()
				.isPresent();
		return isExists;
	}

	@Override
	public User findUserByEmail(String userEmail) {
		return userRepository.findByEmail(userEmail);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordTokenRepository.save(myToken);

	}

	@Override
	public String validatePasswordResetToken(String token) {

		final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
		return !isTokenFound(passToken) ? "invalidToken" : isTokenExpired(passToken) ? "expired" : null;
	}

	private boolean isTokenFound(PasswordResetToken passToken) {
		return passToken != null;
	}

	private boolean isTokenExpired(PasswordResetToken passToken) {

		final Calendar cal = Calendar.getInstance();
		return passToken.getExpiryDate().before(cal.getTime());
	}

	@Override
	public Optional<User> getUserByPasswordResetToken(final String token) {
		return Optional.ofNullable(passwordTokenRepository.findByToken(token).getUser());
	}

	@Override
	public void changeUserPassword(User user, String password) {

		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
	}
}
