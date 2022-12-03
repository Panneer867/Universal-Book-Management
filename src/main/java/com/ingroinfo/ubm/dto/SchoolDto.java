package com.ingroinfo.ubm.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDto {

	private Long schoolId;
	private String schoolName;
	private String board;
	private String stage;
	private String email;
	private String phone;
	private String address;
	private String state;
	private String city;
	private String pinCode;
	private String remarks;
	private Date dateCreated;
	private Date lastUpdated;

}
