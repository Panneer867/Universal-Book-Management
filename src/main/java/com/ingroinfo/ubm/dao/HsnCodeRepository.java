package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Category;
import com.ingroinfo.ubm.entity.HsnCode;

@Repository
public interface HsnCodeRepository extends JpaRepository<HsnCode, Long> {

	HsnCode findByHsnCode(Long hsnCode);

	HsnCode findByHsnId(Long hsnId);

	HsnCode findByCategory(Category category);

}
