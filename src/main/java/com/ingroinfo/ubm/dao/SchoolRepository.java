package com.ingroinfo.ubm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

	School findBySchoolId(Long id);

	School findBySchoolName(String schoolName);

	School findByEmail(String email);
}
