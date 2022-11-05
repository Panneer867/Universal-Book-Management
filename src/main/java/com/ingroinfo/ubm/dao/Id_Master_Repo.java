package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Id_Master;

@Repository
public interface Id_Master_Repo extends JpaRepository<Id_Master, Integer> {

}
