package com.ingroinfo.ubm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "master_item")
@SequenceGenerator(name = "item_sequence", initialValue = 201, allocationSize = 10000)
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branch_sequence")
	private Long itemId;

	private String itemName;

	private String itemImage;

	private String itemStatus;

	private String unitOfMeasure;

	private String description;

	private String units;

	private Long categoryId;

	private Long supplierId;

	private Long brandId;

	private Long publisherId;

	private Long hsnCodeId;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}
