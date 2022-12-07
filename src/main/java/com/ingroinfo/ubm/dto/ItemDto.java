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
	private Long hsnCode;
	private Long categoryId;
	private Long supplierId;
	private Long brandId;
	private Long publisherId;
	private Long hsnCodeId;
	private String unitOfMeasure;
	private String description;
	private String companyName;
	private Date dateCreated;
	private Date lastUpdated;

}
