package com.ingroinfo.ubm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.BrandPublisherRepo;
import com.ingroinfo.ubm.dto.BrandPublisherDTO;
import com.ingroinfo.ubm.entity.BrandPublisher;
import com.ingroinfo.ubm.service.BrandPublisherService;

@Service
public class BrandPublisherServiceImp implements BrandPublisherService {

	@Autowired
	private BrandPublisherRepo brandPublisherRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createBrandPublisherMaster(BrandPublisherDTO brandPublisherDTO) {
		// TODO Auto-generated method stub

		BrandPublisher brandPublisher = new BrandPublisher();
		brandPublisher.setBrandPublisherId(brandPublisherDTO.getBrandPublisherId());
		brandPublisher.setBrand(brandPublisherDTO.getBrand());
		brandPublisher.setSupplier(brandPublisherDTO.getSupplier());
		brandPublisher.setCategoryOf(brandPublisherDTO.getCategoryOf());
		brandPublisher.setBrandLogo(brandPublisherDTO.getBrandLogo());

		brandPublisherRepo.save(brandPublisher);

	}

	@Override
	public List<BrandPublisherDTO> getBrandPublisher() {
		// TODO Auto-generated method stub
		List<BrandPublisher> brand_Publisher = this.brandPublisherRepo.findAll();
		List<BrandPublisherDTO> brand_PublisherDtos = brand_Publisher.stream()
				.map((brandPublisher) -> this.modelMapper.map(brandPublisher, BrandPublisherDTO.class))
				.collect(Collectors.toList());
		return brand_PublisherDtos;
	}

	@Override
	public void deleteBrandPublisher(Integer brandId) {
		// TODO Auto-generated method stub
		brandPublisherRepo.deleteById(brandId);
	}

}
