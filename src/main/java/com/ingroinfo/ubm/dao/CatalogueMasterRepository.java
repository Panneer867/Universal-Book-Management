package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.CatalogueMaster;

@Repository
public interface CatalogueMasterRepository extends JpaRepository<CatalogueMaster, Long> {

}
