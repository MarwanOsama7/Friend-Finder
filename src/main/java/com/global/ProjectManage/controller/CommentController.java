package com.global.ProjectManage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.ProjectManage.dto.CommentDTO;
import com.global.ProjectManage.entity.Comment;
import com.global.ProjectManage.services.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService service;

	@GetMapping("/findall")
	public ResponseEntity<?> findall() {
		return ResponseEntity.ok(service.findall());
	}

	@PostMapping
	public ResponseEntity<Comment> save(@RequestBody CommentDTO com) {
		Comment comment = service.save(com);
		return ResponseEntity.status(HttpStatus.CREATED).body(comment);
	}

	@GetMapping("/finduserid/{id}")
	public ResponseEntity<?> findByUserId(@PathVariable Long id) {
		return ResponseEntity.ok(service.findByUserId(id));
	}

	@GetMapping("/by-post/{postId}")
	public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Long postId) {
		List<CommentDTO> comments = service.getCommentsByPostId(postId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
}
