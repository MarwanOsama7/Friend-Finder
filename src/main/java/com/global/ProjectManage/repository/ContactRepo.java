package com.global.ProjectManage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.ProjectManage.entity.Contact;

public interface ContactRepo extends JpaRepository<Contact, Long>{

}
