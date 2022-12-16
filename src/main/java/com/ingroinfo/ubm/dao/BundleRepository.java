package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Bundle;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, Long> {

	Bundle findByBundleName(String bundleName);

	Bundle findByBundleId(Long id);

}
