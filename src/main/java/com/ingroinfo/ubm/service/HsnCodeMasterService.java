package com.ingroinfo.ubm.service;

import java.util.List;
import com.ingroinfo.ubm.dto.HsnCodeMasterDto;

public interface HsnCodeMasterService {

	HsnCodeMasterDto saveHsnCode(HsnCodeMasterDto hsnCodeDto);

	List<HsnCodeMasterDto> getAllHsnList();

	void deleteHsnData(Long hsnId);

}
