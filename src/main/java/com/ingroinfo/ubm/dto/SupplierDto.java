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
public class SupplierDto {

	private Long supplierId;
	private String supplierName;
	private String reciptNo;
	private String gstType;
	private String gstin;
	private String address;
	private String state;
	private String city;
	private String pinCode;
	private String email;
	private String telephone;
	private String mobile;
	private String faxNo;
	private String bankName;
	private String accountNumber;
	private String ifscCode;
	private String totalPurchasedAmount;
	private String paidAmount;
	private String balanceAmount;
	private Date dateCreated;
	private Date lastUpdated;

}
