package com.ingroinfo.ubm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	private String school;
	private String standard;
	private String medium;
	private String books;
	private String quantity;

}
