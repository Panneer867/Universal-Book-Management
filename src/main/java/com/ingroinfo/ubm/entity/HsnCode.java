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
@Table(name = "hsn_master")
public class HsnCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hsnId;
	
	private String categoryName;
	
	private Long hsnCode;
	
	@CreationTimestamp
	private Date dateCreated;

	@UpdateTimestamp
	private Date lastUpdated;
	
}
