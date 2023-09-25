package com.global.ProjectManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.ProjectManage.entity.Friend;
import com.global.UserManage.entity.AppUser;

public interface FriendRepo extends JpaRepository<Friend, Long>{

	 boolean existsByUserAndFriend(AppUser user, AppUser friend);
}
