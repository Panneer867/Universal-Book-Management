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
public class BrandPublisherDto {

	private Long publisherId;
	private String brand;
	private String category;
	private String supplier;
	private String publisherName;
	private String remarks;
	private Date dateCreated;
	private Date lastUpdated;

}
