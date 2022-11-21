package com.ingroinfo.ubm.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ingroinfo.ubm.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByEmail(String email);

	User findByMobile(String mobile);

	User findByBranchId(Long id);

	User findByUserId(Long id);

	@Transactional
	@Modifying
	@Query("delete from User u where u.branchId=:id")
	void deleteByBranchAssociatedUsers(Long id);

}