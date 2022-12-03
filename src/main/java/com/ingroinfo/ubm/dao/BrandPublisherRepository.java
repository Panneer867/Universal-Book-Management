package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.BrandPublisher;

@Repository
public interface BrandPublisherRepository extends JpaRepository<BrandPublisher, Long> {

	BrandPublisher findByPublisherId(Long publisherId);

	BrandPublisher findByPublisherName(String publisherName);

}
