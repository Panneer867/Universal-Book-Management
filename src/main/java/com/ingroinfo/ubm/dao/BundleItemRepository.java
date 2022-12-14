package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.BundleItem;

@Repository
public interface BundleItemRepository extends JpaRepository<BundleItem, Long> {

}