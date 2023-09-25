package com.global.ProjectManage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.global.ProjectManage.dto.postdto;
import com.global.ProjectManage.entity.Post;

public interface PostRepo extends JpaRepository<Post, Long>{

	List<Post> findByUserId(Long id);
	
	
	@Query("SELECT NEW com.global.ProjectManage.dto.postdto(p.id, p.img, p.text, p.user.id, p.user.firstname) FROM Post p WHERE p.user.id = :userId")
	List<postdto> findPostsByUserId(@Param("userId") Long userId);

}
