package com.global.ProjectManage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.ProjectManage.entity.Post;
import com.global.ProjectManage.services.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService service;

	@GetMapping("/findall")
	public ResponseEntity<?> findall() {
		return ResponseEntity.ok(service.findall());
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody Post post) {
		return ResponseEntity.ok(service.save(post));
	}

	@GetMapping("/finduserid/{id}")
	public ResponseEntity<?> findByUserId(@PathVariable Long id) {
		return ResponseEntity.ok(service.findByUserId(id));
	}
}
