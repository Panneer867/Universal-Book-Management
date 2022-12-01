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
public class HsnDto {

	private Long hsnId;

	private String categoryName;

	private Long hsnCode;

	private Date dateCreated;

	private Date lastUpdated;

}
