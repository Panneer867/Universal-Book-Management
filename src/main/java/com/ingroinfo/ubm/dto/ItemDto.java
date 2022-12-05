package com.ingroinfo.ubm.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

	private Long itemId;
	private String itemName;
	private String itemImage;
	private String itemStatus;
	private String units;
	private String categoryName;
	private String supplierName;
	private String publisherName;
	private String brandName;
	private Long category;
	private Long supplier;
	private Long brand;
	private Long publisher;
	private Long hsnCode;
	private String unitOfMeasure;
	private String remarks;
	private String companyName;
	private Date dateCreated;
	private Date lastUpdated;

}
