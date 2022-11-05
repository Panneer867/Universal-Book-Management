package com.ingroinfo.ubm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "supplier_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierMaster {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	public int supplierId;
	public String supplierCode;
	public String supplierName;
	public String companyName;
	public String alias;
	public String supplierType;
	public String supplierReference;
	public String aboutSupplier;
	public String supplierReferenceContact;
	
	@Column(name = "`doorNo`", length = 5, precision =0) 
	public String doorNo;
	@Column(name = "`cross`", length = 15, precision =0) 
	public String cross;
	public String main;
	public String nearBy;
	public String area;
	public String city;
	public String state;
	public String pinCode;
	public String landlineNumber;
	@Column(name = "`emailId`", length = 50, precision =0) 
	public String emailId;
	public String gstnType;
	public String gstnNumber;
	public String supplierStatus;
	public String mobileNO;
	public String supplierBrand;
	public String panNumber;
	public String uploadGstCrft;
	public String note;
	public String productName;
	public String category;
	public String hsnCode;
	public String accountNumber;
	public String bank;
	public String ifscCode;
	
}
