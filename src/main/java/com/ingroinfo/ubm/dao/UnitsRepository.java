package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.UnitOfMeasures;

@Repository
public interface UnitsRepository extends JpaRepository<UnitOfMeasures, Long> {

	UnitOfMeasures findByUnitId(Long id);

	UnitOfMeasures findByUnitOfMeasure(String unitOfMeasure);

}
