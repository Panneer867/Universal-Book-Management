package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

}
