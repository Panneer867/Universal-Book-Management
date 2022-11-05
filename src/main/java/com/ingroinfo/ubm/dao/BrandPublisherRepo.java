package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.BrandPublisher;

@Repository
public interface BrandPublisherRepo extends JpaRepository<BrandPublisher, Integer> {

}
