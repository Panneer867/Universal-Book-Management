package com.ingroinfo.ubm.entity;

import java.util.Date;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "master_bundle")
public class Bundle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bundleId;
	private String bundleName;
	private Long schoolId;
	private String standard;
	private String medium;
	private double subTotal;
	private double gstPercent;
	private double gstAmt;
	private double grandTotal;

	@CreationTimestamp
	private Date dateCreated;

	@UpdateTimestamp
	private Date lastUpdated;

}
