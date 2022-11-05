package com.ingroinfo.ubm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "id_master")
public class Id_Master {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int allMasterid;
	public String id_type;
	public String serial_number;
	public String last_number;
	public String description;

}
