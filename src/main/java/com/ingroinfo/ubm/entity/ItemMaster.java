package com.ingroinfo.ubm.entity;

import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_master")
public class ItemMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;
	private String itemName;
	private String itemStatus;
	private Date createDate;
	private String categoryName;
	private String authorName;
	private String publisherName;
	private String itemImage;
	private String imageDir;

	@OneToMany(targetEntity = ItemMaster.class, mappedBy = "itemId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

	public Set<ItemMaster> itemMasters;
}
