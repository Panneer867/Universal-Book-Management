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

	private String itemStatus;

	private String categoryId;

	private String supplierId;

	private String brandId;

	private String hsnCode;

	private Date dateCreated;

	private Date lastUpdated;

}
