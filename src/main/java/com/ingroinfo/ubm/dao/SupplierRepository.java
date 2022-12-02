package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	Supplier findByReciptNo(String reciptNo);

	Supplier findByGstin(String gstin);

	Supplier findBySupplierId(Long id);

	Supplier findBySupplierName(String supplierName);

}
