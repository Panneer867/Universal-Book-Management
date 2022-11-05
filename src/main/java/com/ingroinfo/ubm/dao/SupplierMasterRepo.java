package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.SupplierMaster;

@Repository
public interface SupplierMasterRepo extends JpaRepository<SupplierMaster, Integer> {

}
