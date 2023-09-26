package com.global.ProjectManage.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.global.ProjectManage.entity.Friend;
import com.global.UserManage.entity.AppUser;

public interface FriendRepo extends JpaRepository<Friend, Long> {

	boolean existsByUserAndFriend(AppUser user, AppUser friend);

	@Query("SELECT DISTINCT CASE " + "WHEN f.user.id = :userId THEN f.friend.id "
			+ "WHEN f.friend.id = :userId THEN f.user.id " + "ELSE NULL " + "END " + "FROM Friend f "
			+ "WHERE f.user.id = :userId OR f.friend.id = :userId")
	Set<Long> findFriendIdsByUserId(Long userId);
}
