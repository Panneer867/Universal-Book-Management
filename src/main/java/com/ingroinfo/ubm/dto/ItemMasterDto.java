package com.ingroinfo.ubm.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMasterDto {

	private Integer itemId;
	private String itemName;
	private String itemStatus;
	private Date createDate;
	private String categoryName;
	private String authorName;
	private String publisherName;
	private String itemImage;
	private String imageDir;

}
