package com.ingroinfo.ubm.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Item;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Long> {

	Item findByItemId(Long id);

	@Modifying
	@Query("UPDATE Item i SET i.supplierId= :val WHERE i.supplierId = :supplierId")
	void deleteSupplierId(@Param("val") String val, @Param("supplierId") Long supplierId);

	@Modifying
	@Query("UPDATE Item i SET i.brandId= :val WHERE i.brandId = :brandId")
	void deleteBrandId(@Param("val") String val, @Param("brandId") Long brandId);

	@Modifying
	@Query("UPDATE Item i SET i.categoryId= :val WHERE i.categoryId = :categoryId")
	void deleteCategoryId(@Param("val") String val, @Param("categoryId") Long categoryId);

	@Modifying
	@Query("UPDATE Item i SET i.hsnCodeId= :val WHERE i.hsnCodeId = :hsnCodeId")
	void deleteHsnCodeId(@Param("val") String val, @Param("hsnCodeId") Long hsnCodeId);

	@Modifying
	@Query("UPDATE Item i SET i.publisherId= :val WHERE i.publisherId = :publisherId")
	void deletePublisherId(@Param("val") String val, @Param("publisherId") Long publisherId);

	Item findByItemName(String itemName);
}
