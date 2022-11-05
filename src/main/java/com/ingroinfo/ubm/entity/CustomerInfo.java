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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_info")
public class CustomerInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	@Column(name = "CUSTOMER_MOBILE", length = 12, precision = 0)
	private String customerMobile;
	@Column(name = "CUSTOMER_NAME", length = 100, precision = 0)
	private String customerName;
	@Column(name = "ENTRY_DATE", length = 10, precision = 0)
	private String entryDate;
	@Column(name = "CATAGORY", length = 50, precision = 0)
	private String category;
	@Column(name = "CUSTOMER_TYPE", length = 50, precision = 0)
	private String customertype;
	@Column(name = "ALIAS_NAME", length = 50, precision = 0)
	private String aliasName;
	@Column(name = "OB", length = 30, precision = 0)
	private String openingBalance;
	@Column(name = "EMAIL_ID", length = 50, precision = 0)
	private String emailId;
	@Column(name = "CUST_GROUP", length = 50, precision = 0)
	private String custGroup;
	@Column(name = "CITY", length = 30, precision = 0)
	private String city;
	@Column(name = "GST_REG_DEALER", length = 50, precision = 0)
	private String gstRegdDealer;
	@Column(name = "ROUTE", length = 100, precision = 0)
	private String route;
	@Column(name = "ADDRESS", length = 100, precision = 0)
	private String address;
	@Column(name = "STATE", length = 30, precision = 0)
	private String state;
	@Column(name = "PINCODE", length = 20, precision = 0)
	private String pincode;
	@Column(name = "WEBSITE", length = 50, precision = 0)
	private String webSite;
	@Column(name = "GSTN_TYPE", length = 30, precision = 0)
	private String gstnType;
	@Column(name = "GSTN_REG_NO", length = 20, precision = 0)
	private String gstnRegNumber;
	@Column(name = "DATE_OF_BIRTH", length = 10, precision = 0)
	private String dateofBirth;
	@Column(name = "MARRIAGE", length = 50, precision = 0)
	private String marriage;
	@Column(name = "EMD_AMOUNT", length = 10, precision = 0)
	private String emdAmount;
	@Column(name = "EMD_DATE", length = 10, precision = 0)
	private String emdDate;
	@Column(name = "REMARKS", length = 50, precision = 0)
	private String remarks;

	@Column(name = "CREDIT_LIMIT", length = 50, precision = 0)
	private String creditLimit;
	@Column(name = "CREDIT_LIMIT_PERCENT", length = 50, precision = 0)
	private String percentageCreditLimit;
	@Column(name = "USED_CREDIT_LIMIT", length = 50, precision = 0)
	private String usedCreditLimit;
	@Column(name = "USED_CREDIT_LIMIT_PERCENT", length = 50, precision = 0)
	private String percentUsedCreditLimit;
	@Column(name = "NO_OF_CREDIT_DAYS", length = 50, precision = 0)
	private String noOfCreditDays;
	@Column(name = "STATUS", length = 50, precision = 0)
	private String status;
	@Column(name = "PAN_CARD_NO", length = 20, precision = 0)
	private String panCardNo;
	@Column(name = "PAN_CARD_NAME", length = 50, precision = 0)
	private String nameOnPanCard;
	@Column(name = "REMINDERS", length = 50, precision = 0)
	private String reminders;
	@Column(name = "DISPATCH_MODE", length = 50, precision = 0)
	private String dispatchMode;
	@Column(name = "DOCUMENT_THRO", length = 50, precision = 0)
	private String documentThrough;

	@Column(name = "PURCHASE_NO", length = 50, precision = 0)
	private String purchaseNo;
	@Column(name = "INVOICE_AMOUNT", length = 50, precision = 0)
	private String invoiceAmount;
	@Column(name = "PURCHASE_DATE", length = 10, precision = 0)
	private String purchasedDate;
	@Column(name = "PAYMENT", length = 30, precision = 0)
	private String payment;
	@Column(name = "PUCHASE_ITEM", length = 50, precision = 0)
	private String purchaseItem;
	@Column(name = "PURCHASE_QTY", length = 50, precision = 0)
	private String puchaseQty;
	@Column(name = "AMOUNT", length = 30, precision = 0)
	private String amount;
	@Column(name = "TOTAL", length = 50, precision = 0)
	private String total;
	@Column(name = "TOTAL_PURCH_QTY", length = 50, precision = 0)
	private String totalPuchaseQty;
	@Column(name = "TOTAL_AMOUNT", length = 20, precision = 0)
	private String totalAmount;

	@Column(name = "P_CUST_NAME", length = 50, precision = 0)
	private String paymentCustomerName;
	@Column(name = "CUSTOMER_CODE", length = 10, precision = 0)
	private String customerCode;
	@Column(name = "INVOICE_NO", length = 20, precision = 0)
	private String invoicenumber;
	@Column(name = "PAYMENT_INVO_AMOUNT", length = 10, precision = 0)
	private String paymentInvoiceAmount;
	@Column(name = "PAYMENT_INVO_DATE", length = 10, precision = 0)
	private String invoiceDate;
	@Column(name = "PAYMENT_STATUS", length = 50, precision = 0)
	private String paymentStatus;
	@Column(name = "PAYMENT_MODE", length = 50, precision = 0)
	private String paymentMode;
	@Column(name = "PAYMENT_DATE", length = 10, precision = 0)
	private String paymentDate;

	@Column(name = "SCHOOL_NAME", length = 100, precision = 0)
	private String schoolName;
	@Column(name = "SCHOOL_CODE", length = 50, precision = 0)
	private String schoolCode;
	@Column(name = "S_PAYMENT", length = 10, precision = 0)
	private String sechoolpayment;
	@Column(name = "S_ADDRESS", length = 50, precision = 0)
	private String schoolAddress;
	@Column(name = "S_CONTACT_NO", length = 12, precision = 0)
	private String contactNumber;
	@Column(name = "S_EMAIL_ID", length = 50, precision = 0)
	private String schoolEmailId;
	@Column(name = "SP_CONTACT_NO", length = 12, precision = 0)
	private String contactPersonName;
	@Column(name = "DEGIGNATION", length = 30, precision = 0)
	private String designation;
	@Column(name = "GST_TYPE", length = 30, precision = 0)
	private String gstType;
	@Column(name = "GST_REF_NO", length = 20, precision = 0)
	private String gstRefNo;

	@Column(name = "B_NAME", length = 50, precision = 0)
	private String busniessName;
	@Column(name = "B_CODE", length = 10, precision = 0)
	private String busniessCode;
	@Column(name = "B_PAYMENT", length = 10, precision = 0)
	private String busniesspayment;
	@Column(name = "B_ADDRESS", length = 50, precision = 0)
	private String busniessAddress;
	@Column(name = "B_CON_NO", length = 12, precision = 0)
	private String busniessContactNumber;
	@Column(name = "B_EMAIL", length = 50, precision = 0)
	private String businessEmailId;
	@Column(name = "B_CON_PERSION_NAME", length = 12, precision = 0)
	private String businessContactPersonName;
	@Column(name = "B_DEGI", length = 50, precision = 0)
	private String businessDesignation;
	@Column(name = "B_GST_TYPE", length = 20, precision = 0)
	private String businessGstType;
	@Column(name = "B_GST_REF_NO", length = 20, precision = 0)
	private String businessGstRefNo;

}
