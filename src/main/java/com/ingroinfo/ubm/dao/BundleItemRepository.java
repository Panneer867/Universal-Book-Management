package com.ingroinfo.ubm.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.BundleItem;

@Repository
public interface BundleItemRepository extends JpaRepository<BundleItem, Long> {

	@Transactional
	@Modifying
	@Query("delete from BundleItem b where b.bundleId=:bundleId")
	void deleteByBundleId(Long bundleId);

}
