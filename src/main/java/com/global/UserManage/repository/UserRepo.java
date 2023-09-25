package com.global.UserManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.global.UserManage.entity.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);
//    UserDetail findByEmail(String email);
	public boolean existsByEmail(String email);
	
    @Query("SELECT u FROM AppUser u " +
            "LEFT JOIN FETCH u.posts p " +
            "LEFT JOIN FETCH p.comments " +
            "WHERE u.id = :userId")
     AppUser findUserWithPostsAndComments(@Param("userId") Long userId);
}
