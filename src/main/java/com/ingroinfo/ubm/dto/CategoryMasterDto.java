package com.ingroinfo.ubm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryMasterDto {

	private Long categoryId;;
	private String categoryName;
	private String categoryStatus;
	private String hsnCode;
	private String dateCreated;

}
