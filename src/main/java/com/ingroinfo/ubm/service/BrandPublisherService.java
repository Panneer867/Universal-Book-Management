package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.dto.BrandPublisherDTO;

public interface BrandPublisherService {

	// create
	void createBrandPublisherMaster(BrandPublisherDTO brandPublisherDTO);

	// getAll
	List<BrandPublisherDTO> getBrandPublisher();

	// delete
	void deleteBrandPublisher(Integer brandPublisherId);

}
