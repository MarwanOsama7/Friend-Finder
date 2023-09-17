package com.global.UserManage.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.global.UserManage.entity.Role;
import com.global.UserManage.repository.RoleRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepo roleRepo;

	@Transactional(readOnly = true)
	public List<Role> findAll() {

		return roleRepo.findAll();
	}

	public Role findById(Long id) {

		return roleRepo.findById(id).orElse(null);
	}
	
	public Role findByName(String name) {

		return roleRepo.findByName(name);
	}
	
	public Role save(Role entity) {
		return roleRepo.save(entity);
	}
}
