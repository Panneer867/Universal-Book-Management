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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Brand_Publisher")
public class BrandPublisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int brandPublisherId;
	public String brand;
	public String supplier;
	public String categoryOf;
	public String brandLogo;

}
