package com.ingroinfo.ubm.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.dto.CompanyDto;
import com.ingroinfo.ubm.entity.Company;
import com.ingroinfo.ubm.entity.User;

public interface CompanyService {

	void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;

	void saveCompany(Company company);

	Company findByUser(User user);

	void userDetails(CompanyDto companyDto);

	boolean companyExists();

	void editCompany(Company company);

	Company findByCompanyId(Long companyId);

	void saveOldData(Company company);

}
