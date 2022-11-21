package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
