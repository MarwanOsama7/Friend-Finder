package com.global.ProjectManage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.ProjectManage.entity.Comment;
import com.global.ProjectManage.entity.Post;

public interface CommentRepo extends JpaRepository<Comment, Long>{

	List<Post> findByUserId(Long id);
}
