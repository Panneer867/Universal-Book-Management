
package com.ingroinfo.ubm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.TaxMasterRepository;
import com.ingroinfo.ubm.dto.TaxMasterDto;
import com.ingroinfo.ubm.entity.TaxMaster;
import com.ingroinfo.ubm.service.TaxMasterService;

@Service
public class TaxMasterImpl implements TaxMasterService {

	@Autowired
	TaxMasterRepository taxRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<TaxMasterDto> getAllPosts() {
		List<TaxMaster> tax_masterList = this.taxRepo.findAll();
		List<TaxMasterDto> taxMasterDto = tax_masterList.stream()
				.map((taxMaster) -> this.modelMapper.map(taxMaster, TaxMasterDto.class)).collect(Collectors.toList());
		return taxMasterDto;
	}

	@Override
	public void createTaxMasterDto(TaxMasterDto taxMasterDto) {

		TaxMaster taxMaster = new TaxMaster();
		taxMaster.setTaxId(taxMasterDto.getTaxId());
		taxMaster.setCgst(taxMasterDto.getCgst());
		taxMaster.setSgst(taxMasterDto.getSgst());
		taxMaster.setGst(taxMasterDto.getGst());

		taxRepo.save(taxMaster);
	}

	@Override
	public void deleteTaxMasterDto(Long taxId) {

		taxRepo.deleteById(taxId);

	}

	@Override
	public TaxMasterDto updateTaxMasterDto(Long taxId, TaxMasterDto taxMasterDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxMasterDto getTaxMasterDtoById(Long taxId) {
		// TODO Auto-generated method stub
		return null;
	}

}
