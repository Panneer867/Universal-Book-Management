package com.ingroinfo.ubm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.HsnCodeMasterRepository;
import com.ingroinfo.ubm.dto.HsnCodeMasterDto;
import com.ingroinfo.ubm.entity.HsnCodeMaster;
import com.ingroinfo.ubm.service.HsnCodeMasterService;

@Service
public class HsnCodeMasterServiceImpl implements HsnCodeMasterService {

	@Autowired
	private HsnCodeMasterRepository hsnCodeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public HsnCodeMasterDto saveHsnCode(HsnCodeMasterDto hsnCodeDto) {

		HsnCodeMaster hsnCode = this.modelMapper.map(hsnCodeDto, HsnCodeMaster.class);
		HsnCodeMaster savedHsnCode = hsnCodeRepository.save(hsnCode);
		return modelMapper.map(savedHsnCode, HsnCodeMasterDto.class);
	}

	@Override
	public List<HsnCodeMasterDto> getAllHsnList() {

		List<HsnCodeMaster> hsnCodeMasters = this.hsnCodeRepository.findAll();
		List<HsnCodeMasterDto> hsnCodeMasterDtos = hsnCodeMasters.stream()
				.map((hsnCode) -> this.modelMapper.map(hsnCode, HsnCodeMasterDto.class)).collect(Collectors.toList());
		return hsnCodeMasterDtos;
	}

	@Override
	public void deleteHsnData(Long hsnId) {

		hsnCodeRepository.deleteById(hsnId);

	}

}
