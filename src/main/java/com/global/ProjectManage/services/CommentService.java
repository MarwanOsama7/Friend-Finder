package com.global.ProjectManage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.ProjectManage.dto.CommentDTO;
import com.global.ProjectManage.entity.Comment;
import com.global.ProjectManage.entity.Post;
import com.global.ProjectManage.error.RecordNotFoundException;
import com.global.ProjectManage.repository.CommentRepo;
import com.global.ProjectManage.repository.PostRepo;
import com.global.UserManage.entity.AppUser;
import com.global.UserManage.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepo repo;
	private final PostRepo postRepository;
	private final UserRepo userRepository;

	public List<Comment> findall() {
		return repo.findAll();
	}

	public Comment save(CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setText(commentDTO.getText());

		// Set the user and post for the comment
		AppUser user = userRepository.findById(commentDTO.getUserId())
				.orElseThrow(() -> new RecordNotFoundException("User not found"));
		Post post = postRepository.findById(commentDTO.getPostId())
				.orElseThrow(() -> new RecordNotFoundException("Post not found"));

		comment.setUser(user);
		comment.getPosts().add(post);

		return repo.save(comment);
	}

	public List<Post> findByUserId(Long id) {
		return repo.findByUserId(id);
	}

	public List<CommentDTO> getCommentsByPostId(Long postId) {
	    return repo.findCommentDTOByPostId(postId);
	}
}
