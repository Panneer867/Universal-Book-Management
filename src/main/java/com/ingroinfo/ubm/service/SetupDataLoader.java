package com.ingroinfo.ubm.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.ingroinfo.ubm.dao.PrivilegeRepository;
import com.ingroinfo.ubm.dao.RoleRepository;
import com.ingroinfo.ubm.entity.Privilege;
import com.ingroinfo.ubm.entity.Role;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;

		Privilege branchPrivilege = createPrivilegeIfNotFound("BRANCH_PRIVILEGE");
		Privilege userPrivilege = createPrivilegeIfNotFound("USER_PRIVILEGE");
		Privilege employeePrivilege = createPrivilegeIfNotFound("EMPLOYEE_PRIVILEGE");

		List<Privilege> ownerPrivileges = Arrays.asList(branchPrivilege, userPrivilege, employeePrivilege);
		createRoleIfNotFound("ROLE_OWNER", ownerPrivileges);
		createRoleIfNotFound("ROLE_MANAGER", Arrays.asList(branchPrivilege));
		createRoleIfNotFound("ROLE_USER", Arrays.asList(userPrivilege));
		createRoleIfNotFound("ROLE_EMPLOYEE", Arrays.asList(employeePrivilege));

		alreadySetup = true;
	}

	@Transactional
	Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

}
