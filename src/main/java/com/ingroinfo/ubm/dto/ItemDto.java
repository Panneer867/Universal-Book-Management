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

	private Long category;

	private String categoryName;

	private Long supplier;

	private String supplierName;

	private Long brand;

	private String brandName;

	private Long hsnCode;

	private String unitOfMeasure;

	private String remarks;

	private String companyName;

	private Date dateCreated;

	private Date lastUpdated;

}
