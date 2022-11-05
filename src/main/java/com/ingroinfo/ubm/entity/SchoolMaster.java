package com.ingroinfo.ubm.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "school_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int schoolid;
	public String schoolCode;
	public String schoolName;
	public String board;
	public String schoolAddress;

	@CreationTimestamp
	private Date dateCreated;

	@UpdateTimestamp
	private Date lastUpdated;

}
