package com.ingroinfo.ubm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchDto {

	private Long branchId;
	private String branchName;
	private String firstName;
	private String mobile;
	private String email;
	private String designation;
	private String address;
	private String state;
	private String pinCode;
	private String city;
	private String username;
	private String password;
	private String dateCreated;
	private String lastUpdated;

}
