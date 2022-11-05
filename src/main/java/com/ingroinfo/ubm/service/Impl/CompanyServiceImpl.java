package com.ingroinfo.ubm.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.ubm.dao.CompanyOldRepository;
import com.ingroinfo.ubm.dao.CompanyRepository;
import com.ingroinfo.ubm.dao.UserRepository;
import com.ingroinfo.ubm.dto.CompanyDto;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;
import com.ingroinfo.ubm.modified.CompanyModifiedData;
import com.ingroinfo.ubm.service.CompanyService;
import com.ingroinfo.ubm.service.UserService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyOldRepository companyOldRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public UserService userService;

	@Override
	public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}

	}

	@Override
	public void saveCompany(Company company) {

		User user = userRepository.findByEmail(company.getEmail());

		if (company.getEmail().equals(user.getEmail())) {
			company.setUser(user);
		}

		companyRepository.save(company);
	}

	@Override
	public Company findByUser(User user) {

		return companyRepository.findByUser(user);
	}

	@Override
	public void userDetails(CompanyDto companyDto) {

		User user = new User();
		user.setFirstName(companyDto.getFirstName());
		user.setEmail(companyDto.getEmail());
		user.setMobile(companyDto.getMobile());
		user.setUsername(companyDto.getUsername());
		user.setPassword(companyDto.getPassword());
		user.setUserType("Company Owner");

		userService.registerCompany(user);
	}

	@Override
	public boolean companyExists() {

		return companyRepository.findTopByOrderByCompanyIdDesc() != null;
	}

	@Override
	public void editCompany(Company company) {

		companyRepository.save(company);
	}

	@Override
	public Company findByCompanyId(Long id) {

		return companyRepository.findByCompanyId(id);
	}

	@Override
	public void deleteCompany(Long companyId) {

		companyRepository.deleteById(companyId);

	}

	@Override
	public void saveOldData(Company company) {

		CompanyModifiedData data = new CompanyModifiedData();

		data.setAadhaar(company.getAadhaar());
		data.setAccountNumber(company.getAccountNumber());
		data.setAccountType(company.getAccountType());
		data.setAlternateMobile(company.getAlternateMobile());
		data.setBankAddress(company.getBankAddress());
		data.setBankCity(company.getBankCity());
		data.setBankName(company.getBankName());
		data.setBankPinCode(company.getBankPinCode());
		data.setBankState(company.getBankState());
		data.setBeneficiaryName(company.getBeneficiaryName());
		data.setBloodGroup(company.getBloodGroup());
		data.setBusinessType(company.getBusinessType());
		data.setCompanyAddress(company.getCompanyAddress());
		data.setCompanyCity(company.getCompanyCity());
		data.setCompanyId(company.getCompanyId());
		data.setCompanyLogo(company.getCompanyLogo());
		data.setCompanyName(company.getCompanyName());
		data.setCompanyPath(company.getCompanyPath());
		data.setCompanyPinCode(company.getCompanyPinCode());
		data.setCompanyState(company.getCompanyState());
		data.setDateCreated(company.getDateCreated());
		data.setDateOfBirth(company.getDateOfBirth());
		data.setDateOfEstablished(company.getDateOfEstablished());
		data.setDrivingLicense(company.getDrivingLicense());
		data.setEmail(company.getEmail());
		data.setEnableApp(company.getEnableApp());
		data.setEnableWeb(company.getEnableWeb());
		data.setEntryDate(company.getEntryDate());
		data.setFatherName(company.getFatherName());
		data.setFax(company.getFax());
		data.setFirstName(company.getFirstName());
		data.setLastName(company.getLastName());
		data.setGender(company.getGender());
		data.setGstin(company.getGstin());
		data.setIfscCode(company.getIfscCode());
		data.setServiceType(company.getServiceType());
		data.setYearsPreviouslyRegistered(company.getYearsPreviouslyRegistered());
		data.setWebsite(company.getWebsite());
		data.setUser(company.getUser().getId());
		data.setTelephone(company.getTelephone());
		data.setSignature(company.getSignature());
		data.setQualification(company.getQualification());
		data.setProfile(company.getProfile());
		data.setPlaceOfBirth(company.getPlaceOfBirth());
		data.setPan(company.getPan());
		data.setOwnerState(company.getOwnerState());
		data.setOwnerPinCode(company.getOwnerPinCode());
		data.setOwnerEmail(company.getOwnerEmail());
		data.setOwnerCity(company.getOwnerCity());
		data.setOwnerAddress(company.getOwnerAddress());
		data.setNoOfBranch(company.getNoOfBranch());
		data.setMobile(company.getMobile());

		companyOldRepository.save(data);
	}

}
