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
@Table(name = "temp_bundle_items")
public class TempBundleItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rowId;
	private Long itemId;
	private Long slNo;
	private String itemName;
	private double itemMrp;
	private Long quantity;
	private double totalCost;
}
