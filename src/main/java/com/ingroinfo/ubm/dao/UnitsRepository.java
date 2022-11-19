package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingroinfo.ubm.entity.UnitOfMeasures;

public interface UnitsRepository extends JpaRepository<UnitOfMeasures, Long> {

	UnitOfMeasures findByUnitId(Long id);

}
