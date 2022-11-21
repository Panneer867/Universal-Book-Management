package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Privilege;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Privilege findByName(String name);

}