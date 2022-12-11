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
@Table(name = "bundled_items")
public class BundledItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bundledItemId;
	private Long bundleId;
	private Long itemId;
	private String itemName;
	private String itemMrp;
	private String quantity;

}
