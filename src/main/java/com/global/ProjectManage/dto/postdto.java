package com.global.ProjectManage.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class postdto {
	
	private long id;
    private String img;
    private String text;
    private long userId;
    private String username; // Include user-related fields as needed
    private LocalDate date;
}
