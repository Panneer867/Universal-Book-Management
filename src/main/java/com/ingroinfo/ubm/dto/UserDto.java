package com.ingroinfo.ubm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long userId;
	private Long branchId;
	private String firstName;
	private String username;
	private String password;
	private String matchingPassword;
	private String newPassword;
	private String email;
	private String mobile;
	private String branch;
	private String role;
	private String userType;
	private String dateCreated;
	private String lastUpdated;

}
