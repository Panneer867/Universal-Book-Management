package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.TaxMaster;

@Repository
public interface TaxMasterRepository extends JpaRepository<TaxMaster, Long> {

}
