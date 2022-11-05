package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.dto.BundleDTO;

public interface BundleMasterService {

	// create
	void createBundleMaster(BundleDTO bundleDTO);

	// getAll
	List<BundleDTO> getAllBundeList();

	// delete
	void deletebundle(Integer bundleId);

	// search
	List<BundleDTO> searchBundles(String keyword);
}
