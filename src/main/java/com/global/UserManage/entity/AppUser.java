package com.global.UserManage.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.global.ProjectManage.entity.Comment;
import com.global.ProjectManage.entity.Friend;
import com.global.ProjectManage.entity.Post;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String firstname;
	private String password;
	private String age;
	private String phone;
	private String gender;
	
	@JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

	@JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> comment;
	
	@JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Friend> friends;
    
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "sec_user_role",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OrderColumn(name = "id")
	private Set<Role> role = new HashSet<>();
	
	public AppUser(Long id) {
		super();
		this.id = id;
	}

	public AppUser(Long id, String email, String firstname, String password, String age, String phone, String gender,
			Set<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.firstname = firstname;
		this.password = password;
		this.age = age;
		this.phone = phone;
		this.gender = gender;
		this.role = roles;
	}
}
