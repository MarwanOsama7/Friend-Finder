package com.global.UserManage.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.UserManage.entity.AppUser;
import com.global.UserManage.secEntity.JwtToken;
import com.global.UserManage.secEntity.SignInRequest;
import com.global.UserManage.security.TokenUtil;
import com.global.UserManage.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final TokenUtil tokenUtil;
	private final UserService service;

	@PostMapping("/signin")
	public JwtToken signin(@RequestBody SignInRequest signInRequest) {
		return tokenUtil.signin(signInRequest);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> save(@RequestBody AppUser user) {
		return ResponseEntity.ok(service.save(user));
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<?> findbyid(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	@GetMapping("/finduser/{email}")
	public ResponseEntity<?> finduser(@PathVariable String email) {
		return ResponseEntity.ok(service.finduser(email));
	}
	@GetMapping("/userid/{userId}")
	public AppUser getUserWithPostsAndComments(@PathVariable Long userId) {
		return service.getUserWithPostsAndComments(userId);
	}

	@GetMapping("/findall")
	public List<AppUser> getAllUsersExceptCurrent() {
		return service.findAllUsersNotFriendsWithCurrentUser();
	}
}
