package com.global.ProjectManage.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.global.ProjectManage.entity.Contact;
import com.global.ProjectManage.repository.ContactRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {

	private final ContactRepo repo;
	
	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return repo.findAll();
	}
	 
	public Contact save(Contact contact) {
		return repo.save(contact);
	}
}
