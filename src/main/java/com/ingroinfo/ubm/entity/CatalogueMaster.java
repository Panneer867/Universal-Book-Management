package com.ingroinfo.ubm.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catalogue_master")
public class CatalogueMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long catalogueId;
	private String supplierName;
	private String categoryName;
	private String itemDesc;
	private String basicPrice;
	private String mrpPrice;
	private String purchasePrice;
	private Date purchaseDate;
	private String discountMax;
	private String sellingPrice;
	private String revisedRate1;
	private Date rateRevisedDate1;
	private String revisedRate2;
	private Date rateRevisedDate2;
	private String revisedRate3;
	private Date rateRevisedDate3;
	private String barcodeCategoryNo;
	private String barcodeItemno;
	private String hsnCode;
	private String units;
	private String gstType;
	private String gst;
	private String iGST;
	private String cGST;
	private String sGST;
	private String attidue1;
	private String attidue2;
	private String attidue3;
	private String attidue4;
	private String attidue5;
	private String attidue6;
	private String attidue7;
	private String attidue8;
	private String uploadImage;
	private String imagePath;

}
