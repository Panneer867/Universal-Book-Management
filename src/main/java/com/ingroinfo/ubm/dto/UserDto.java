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

	private Long id;
	private String firstName;
	private String username;
	private String password;
	private String email;
	private String mobile;
	private String branch;
	private String role;
	private String userType;
	private String dateCreated;
	private String lastUpdated;
	private Long branchId;

}
