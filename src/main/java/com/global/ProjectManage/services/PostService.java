package com.global.ProjectManage.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.global.ProjectManage.dto.postdto;
import com.global.ProjectManage.entity.Post;
import com.global.ProjectManage.error.RecordNotFoundException;
import com.global.ProjectManage.repository.PostRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepo repo;

	public List<postdto> findall() {
		List<Post> posts = repo.findAll();

		// Convert Post entities to CustomPostResponse objects
		return posts.stream().map(post -> {
			postdto response = new postdto();
			response.setId(post.getId());
			response.setImg(post.getImg());
			response.setText(post.getText());
			response.setDate(post.getDate());
			if (post.getUser() != null) {
				response.setUserId(post.getUser().getId());
				response.setUsername(post.getUser().getFirstname());
				// Include other user-related fields as needed
			}

			return response;
		}).collect(Collectors.toList());
	}

	public Post save(Post post) {
		post.setDate(LocalDate.now());
		return repo.save(post);
	}

	public List<postdto> findPostsByUserId(Long id) {
		if (id == null) {
			throw new RecordNotFoundException("not posts shared");
		}
		return repo.findPostsByUserId(id);
	}
	
	public Post findById(Long id) {
		return repo.findById(id).orElse(null);
	}
}
