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
@Table(name = "brand_master")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brandId;

	private String brandName;

	private String brandLogo;

	@CreationTimestamp
	private Date dateCreated;

	@UpdateTimestamp
	private Date lastUpdated;
}
