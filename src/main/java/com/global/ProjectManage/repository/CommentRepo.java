package com.global.ProjectManage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.global.ProjectManage.dto.CommentDTO;
import com.global.ProjectManage.entity.Comment;
import com.global.ProjectManage.entity.Post;

public interface CommentRepo extends JpaRepository<Comment, Long> {

	List<Post> findByUserId(Long id);

	@Query("SELECT NEW com.global.ProjectManage.dto.CommentDTO(c.id, c.text, c.user.firstname, c.user.id, p.id) FROM Comment c JOIN c.posts p WHERE p.id = :postId")
	List<CommentDTO> findCommentDTOByPostId(Long postId);

}
