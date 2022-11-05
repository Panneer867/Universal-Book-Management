package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.SchoolMaster;

@Repository
public interface School_MasterRepo extends JpaRepository<SchoolMaster, Integer> {

}
