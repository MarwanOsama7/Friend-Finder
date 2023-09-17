package com.global.UserManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.UserManage.entity.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
//    UserDetail findByEmail(String email);
	public boolean existsByEmail(String email);
}
