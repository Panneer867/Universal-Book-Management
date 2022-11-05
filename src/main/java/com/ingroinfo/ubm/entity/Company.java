package com.ingroinfo.ubm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
@SequenceGenerator(name = "company_sequence", initialValue = 1001, allocationSize = 100)
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_sequence")
	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "business_type")
	private String businessType;

	@Column(name = "date_of_established")
	private String dateOfEstablished;

	@Column(name = "service_type")
	private String serviceType;

	@Column(name = "registered_date")
	private String yearsPreviouslyRegistered;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "company_email")
	private String email;

	@Column(name = "fax")
	private String fax;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "company_address")
	private String companyAddress;

	@Column(name = "company_state")
	private String companyState;

	@Column(name = "company_city")
	private String companyCity;

	@Column(name = "company_pincode")
	private String companyPinCode;

	@Column(name = "website")
	private String website;

	@Column(name = "company_logo")
	private String companyLogo;

	@Column(name = "no_of_branch")
	private String noOfBranch;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "gender")
	private String gender;

	@Column(name = "qualification")
	private String qualification;

	@Column(name = "owner_email")
	private String ownerEmail;

	@Column(name = "mobile_no")
	private String mobile;

	@Column(name = "alternate_mobile")
	private String alternateMobile;

	@Column(name = "aadhaar")
	private String aadhaar;

	@Column(name = "pan")
	private String pan;

	@Column(name = "driving_license")
	private String drivingLicense;

	@Column(name = "owner_address")
	private String ownerAddress;

	@Column(name = "owner_state")
	private String ownerState;

	@Column(name = "owner_city")
	private String ownerCity;

	@Column(name = "owner_pincode")
	private String ownerPinCode;

	@Column(name = "place_of_birth")
	private String placeOfBirth;

	@Column(name = "profile")
	private String profile;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "beneficiary_name")
	private String beneficiaryName;

	@Column(name = "account_ype")
	private String accountType;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "ifsc_code")
	private String ifscCode;

	@Column(name = "bank_address")
	private String bankAddress;

	@Column(name = "bank_state")
	private String bankState;

	@Column(name = "bank_city")
	private String bankCity;

	@Column(name = "bank_pincode")
	private String bankPinCode;

	@Column(name = "signature")
	private String signature;

	@Column(name = "entryDate")
	private String entryDate;

	@Column(name = "enable_web")
	private String enableWeb;

	@Column(name = "enable_app")
	private String enableApp;

	@Column(name = "company_path")
	private String companyPath;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
