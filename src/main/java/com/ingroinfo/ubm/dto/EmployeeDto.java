package com.ingroinfo.ubm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

	private Long employeeId;
	private String firstName;
	private String lastName;
	private String profile;
	private String fatherName;
	private String dateOfBirth;
	private String bloodGroup;
	private String mobile;
	private String alternateMobile;
	private String placeOfBirth;
	private String gender;
	private String email;
	private String maritalStatus;
	private String qualification;
	private String address;
	private String state;
	private String pinCode;
	private String aadhaar;
	private String city;
	private String drivingLicense;
	private Long companyId;
	private String dateCreated;
	private String lastUpdated;

}
