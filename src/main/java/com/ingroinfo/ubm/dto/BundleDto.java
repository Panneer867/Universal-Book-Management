package com.ingroinfo.ubm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BundleDto {

	private Long bundleId;
	private String bundleName;
	private String school;
	private String standard;
	private String medium;
	private Long itemId;
	private Long slNo;
	private String itemName;
	private String itemMrp;
	private String itemExists;
	private Long quantity;
}
