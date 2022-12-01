package com.ingroinfo.ubm.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	private String itemStatus;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categoryId;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplierId;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brandId;

	@ManyToOne
	@JoinColumn(name = "hsn_id")
	private HsnCode hsnCode;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

}
