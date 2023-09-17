package com.global.UserManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.UserManage.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName (String name);
}
