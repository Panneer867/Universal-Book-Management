package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.modified.CompanyModifiedData;

@Repository
public interface CompanyOldRepository extends JpaRepository<CompanyModifiedData, Long> {

}
