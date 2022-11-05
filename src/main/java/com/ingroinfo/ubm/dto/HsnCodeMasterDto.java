package com.ingroinfo.ubm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HsnCodeMasterDto {

	private Long hsnId;
	private String itemName;
	private String catagoryName;
	private String hsnCode;
}
