package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.BundledItem;

@Repository
public interface BundledItemRepository extends JpaRepository<BundledItem, Long> {

	BundledItem findByItemId(Long itemId);

	BundledItem findByItemName(String itemName);

}
