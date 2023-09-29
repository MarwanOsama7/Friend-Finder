package com.global.ProjectManage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.ProjectManage.entity.Contact;
import com.global.ProjectManage.services.ContactService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

	private final ContactService service;
	
	@GetMapping("/findall")
	public ResponseEntity<?> findall() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Contact> save(@RequestBody Contact com) {
		Contact comment = service.save(com);
		return ResponseEntity.status(HttpStatus.CREATED).body(comment);
	}
	
}
