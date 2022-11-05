package com.ingroinfo.ubm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandPublisherDTO {

	public int brandPublisherId;
	public String brand;
	public String supplier;
	public String categoryOf;
	public String brandLogo;

}
