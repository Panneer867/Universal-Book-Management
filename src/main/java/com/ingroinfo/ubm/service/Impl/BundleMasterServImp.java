package com.ingroinfo.ubm.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingroinfo.ubm.dao.BundleMasterRepo;
import com.ingroinfo.ubm.dto.BundleDTO;
import com.ingroinfo.ubm.entity.Bundle;
import com.ingroinfo.ubm.service.BundleMasterService;

@Service
public class BundleMasterServImp implements BundleMasterService {

	@Autowired
	private BundleMasterRepo bundleMasterRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createBundleMaster(BundleDTO bundleDTO) {
		// TODO Auto-generated method stub

		Bundle bundle = new Bundle();
		bundle.setBundleId(bundleDTO.getBundleId());
		bundle.setCreateBundleId(bundleDTO.getCreateBundleId());
		bundle.setBundleName(bundleDTO.getBundleName());
		bundle.setBundleBoard(bundleDTO.getBundleBoard());
		bundle.setBundleSchoolName(bundleDTO.getBundleSchoolName());
		bundle.setBundleStanded(bundleDTO.getBundleStanded());
		bundle.setBundleAddress(bundleDTO.getBundleAddress());
		bundle.setBundleLanguage(bundleDTO.getBundleLanguage());
		bundle.setBundleBookName(bundleDTO.getBundleBookName());
		bundle.setBundleQty(bundleDTO.getBundleQty());
		bundle.setBundlePrice(bundleDTO.getBundlePrice());
		bundleMasterRepo.save(bundle);
	}

	@Override
	public List<BundleDTO> getAllBundeList() {
		List<Bundle> bundleMasters = this.bundleMasterRepo.findAll();
		List<BundleDTO> brandleMasterDtos = bundleMasters.stream()
				.map((bundle) -> this.modelMapper.map(bundle, BundleDTO.class)).collect(Collectors.toList());

		return brandleMasterDtos;
	}

	@Override
	public void deletebundle(Integer bundleId) {

		bundleMasterRepo.deleteById(bundleId);
	}

	@Override
	public List<BundleDTO> searchBundles(String keyword) {
		List<Bundle> bundles = this.bundleMasterRepo.findByBundleBookName(keyword);
		List<BundleDTO> brandleDtos = bundles.stream().map((bundle) -> this.modelMapper.map(bundle, BundleDTO.class))
				.collect(Collectors.toList());

		return brandleDtos;
	}

}
