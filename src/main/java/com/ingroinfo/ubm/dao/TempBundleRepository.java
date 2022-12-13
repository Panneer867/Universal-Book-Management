package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.TempBundle;

@Repository
public interface TempBundleRepository extends JpaRepository<TempBundle, Long> {

	TempBundle findByItemId(Long itemId);

	TempBundle findByItemName(String itemName);


}
