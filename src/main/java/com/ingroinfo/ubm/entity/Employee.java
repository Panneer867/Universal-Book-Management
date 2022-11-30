package com.ingroinfo.ubm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long employeeId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "profile")
	private String profile;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "mobile_no")
	private String mobile;

	@Column(name = "alternate_mobile")
	private String alternateMobile;

	@Column(name = "place_of_birth")
	private String placeOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "qualification")
	private String qualification;

	@Column(name = "address")
	private String address;

	@Column(name = "state")
	private String state;

	@Column(name = "pin_code")
	private String pinCode;

	@Column(name = "aadhaar")
	private String aadhaar;

	@Column(name = "city")
	private String city;

	@Column(name = "driving_license")
	private String drivingLicense;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}
