package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.BrandMaster;

@Repository
public interface BrandMasterRepository extends JpaRepository<BrandMaster, Long> {

}
