package com.ingroinfo.ubm.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Bundle;

@Repository
public interface BundleMasterRepo extends JpaRepository<Bundle, Integer> {

	List<Bundle> findByBundleBookName(String bundleBookName);

}
