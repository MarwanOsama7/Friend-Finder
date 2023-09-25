package com.global.ProjectManage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.ProjectManage.services.FriendService;
import com.global.UserManage.entity.AppUser;
import com.global.UserManage.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

	private final FriendService service;
	private final UserService appUserRepository;

	@GetMapping("/findall")
	public ResponseEntity<?> findall() {
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping("/add")
	public ResponseEntity<String> addFriend(@RequestParam Long userId, @RequestParam Long friendId) {
		AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		AppUser friend = appUserRepository.findById(friendId)
				.orElseThrow(() -> new RuntimeException("Friend not found"));

		service.addFriend(user, friend);
		return ResponseEntity.ok("Friend added successfully");
	}

	@GetMapping("/areFriends")
	public ResponseEntity<Boolean> areFriends(@RequestParam Long userId, @RequestParam Long friendId) {
		AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		AppUser friend = appUserRepository.findById(friendId)
				.orElseThrow(() -> new RuntimeException("Friend not found"));

		boolean areFriends = service.areFriends(user, friend);
		return ResponseEntity.ok(areFriends);
	}

}
