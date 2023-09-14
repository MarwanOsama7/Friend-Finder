package com.global.ProjectManage.entity;

import com.global.UserManage.entity.AppUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

	private Long id;
	private String img;
	private String text;
	private int like;
	private AppUser user;
}
