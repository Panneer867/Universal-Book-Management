package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.dto.TaxMasterDto;

public interface TaxMasterService {

	// Listing
	List<TaxMasterDto> getAllPosts();

	// Creating
	void createTaxMasterDto(TaxMasterDto taxMasterDto);

	// Updating
	TaxMasterDto updateTaxMasterDto(Long taxId, TaxMasterDto taxMasterDto);

	// Deleting
	void deleteTaxMasterDto(Long taxId);

	// Getting
	TaxMasterDto getTaxMasterDtoById(Long taxId);

}
