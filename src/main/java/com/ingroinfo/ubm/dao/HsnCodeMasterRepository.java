package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.HsnCodeMaster;

@Repository
public interface HsnCodeMasterRepository extends JpaRepository<HsnCodeMaster, Long> {

}
