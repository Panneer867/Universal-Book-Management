package com.ingroinfo.ubm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Master_Brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandMaster {
	
	@Id
	private Long  brandId;
	@Column(name="brand_name",nullable =false,length = 100)
	private String brandName;
	private String brandLogo;
	private String LogoDir;

}
