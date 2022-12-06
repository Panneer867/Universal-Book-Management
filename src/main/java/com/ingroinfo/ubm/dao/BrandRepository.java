package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

	Brand findByBrandName(String brandName);

	Brand findByBrandId(Long id);
}
