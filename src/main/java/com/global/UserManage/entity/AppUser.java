package com.global.UserManage.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

	private Long id;
	private String firstname;
	private String lastname;
	private String age;
	private String phone;
	private Date date;
	private String gender;
}
