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
public class Friend {

	private long id;

	private int idFriend;

	private AppUser user;
}
