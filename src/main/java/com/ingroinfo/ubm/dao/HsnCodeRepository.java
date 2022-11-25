package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ingroinfo.ubm.entity.HsnCode;

public interface HsnCodeRepository extends JpaRepository<HsnCode, Long> {

	HsnCode findByHsnCode(Long hsnCode);

	HsnCode findByCategoryName(String categoryName);

	HsnCode findByHsnId(Long hsnId);

}
