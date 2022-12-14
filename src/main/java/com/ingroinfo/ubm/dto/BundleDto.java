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
	private Long schoolId;
	private String standard;
	private String medium;
	private Long itemId;
	private Long slNo;
	private String itemName;
	private double itemMrp;
	private String itemExists;
	private Long quantity;
	private double totalCost;
	private double subTotal;
	private double gstPercent;
	private double gstAmt;
	private double grandTotal;
}
