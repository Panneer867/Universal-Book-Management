package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.TempBundleItem;

@Repository
public interface TempBundleRepository extends JpaRepository<TempBundleItem, Long> {

	TempBundleItem findByItemId(Long itemId);
	TempBundleItem findByItemName(String itemName);
}
