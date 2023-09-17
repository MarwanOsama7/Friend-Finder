package com.global.ProjectManage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.ProjectManage.entity.Comment;
import com.global.ProjectManage.entity.Post;
import com.global.ProjectManage.repository.CommentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepo repo;

	public List<Comment> findall() {
		return repo.findAll();
	}

	public Comment save(Comment com) {
		return repo.save(com);
	}

	public List<Post> findByUserId(Long id) {
		return repo.findByUserId(id);
	}
}
