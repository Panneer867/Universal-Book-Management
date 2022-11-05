package com.ingroinfo.ubm.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogueMasterDto {

	private Long catalogueId;
	private String supplierName;
	private String categoryName;
	private String itemDesc;
	private String basicPrice;
	private String mrpPrice;
	private String purchasePrice;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date purchaseDate;
	private String discountMax;
	private String sellingPrice;
	private String revisedRate1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rateRevisedDate1;
	private String revisedRate2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rateRevisedDate2;
	private String revisedRate3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
