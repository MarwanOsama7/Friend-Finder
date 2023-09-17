package com.global.ProjectManage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.ProjectManage.entity.Post;

public interface PostRepo extends JpaRepository<Post, Long>{

	List<Post> findByUserId(Long id);
}
