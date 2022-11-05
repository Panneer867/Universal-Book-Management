package com.ingroinfo.ubm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Bundle_Master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bundle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bundleId;
	private String createBundleId;
	private String bundleName;
	private String bundleBoard;
	private String bundleSchoolName;
	private String bundleStanded;
	private String bundleAddress;
	private String bundleLanguage;
	private String bundleBookName;
	private String bundleQty;
	private String bundlePrice;

	@ManyToOne
	@JoinColumn(name = "itemId")
	private ItemMaster itrItemMaster;

}
