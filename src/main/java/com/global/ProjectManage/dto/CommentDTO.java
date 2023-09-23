package com.global.ProjectManage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
	private Long id;
	private String text;
	private String username;
	private Long userId;
	private Long postId;
	
	
}
