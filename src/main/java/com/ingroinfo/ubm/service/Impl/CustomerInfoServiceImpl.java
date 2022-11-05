package com.ingroinfo.ubm.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.CustomerInfoRepository;
import com.ingroinfo.ubm.dto.CustomerInfoDto;
import com.ingroinfo.ubm.entity.CustomerInfo;
import com.ingroinfo.ubm.service.CustomerInfoService;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
	
	@Autowired
	private CustomerInfoRepository customerInfoRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerInfoDto createCustomerInfo(CustomerInfoDto customerInfoDto) {
	
		CustomerInfo customerInfo = this.modelMapper.map(customerInfoDto, CustomerInfo.class);
		CustomerInfo saveCustomerInfo = customerInfoRepo.save(customerInfo);
	
		return this.modelMapper.map(saveCustomerInfo, CustomerInfoDto.class);
	}

}
