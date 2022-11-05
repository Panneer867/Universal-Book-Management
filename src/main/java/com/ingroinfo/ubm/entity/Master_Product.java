package com.ingroinfo.ubm.entity;

import javax.persistence.Entity;
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
@Table(name = "master_product")
public class Master_Product {

	@Id
	private Integer masterProductId;
	private Double barCode;
	private String productName;
	private String alias;
	private String cataregy;
	private String brand;
	private Double unit;

}
