package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	Item findByItemId(Long id);

}
