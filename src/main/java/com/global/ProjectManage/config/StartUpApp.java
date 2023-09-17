package com.global.ProjectManage.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.UserManage.entity.Role;
import com.global.UserManage.services.RoleService;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class StartUpApp implements CommandLineRunner {


//	private final PostService postService;
//	private final CommentService comService;
	private final RoleService roleService;
//	private final UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		if (roleService.findAll().isEmpty()) {
			roleService.save(new Role(null,"admin"));
			roleService.save(new Role(null, "user"));
			roleService.save(new Role(null, "employee"));
		}
//		if (userService.findall().isEmpty()) {
//
//			Set<Role> adminRoles = new HashSet<>();
//			adminRoles.add(roleService.findByName("admin"));
//
//			Set<Role> userRoles = new HashSet<>();
//			userRoles.add(roleService.findByName("user"));
//
//			Set<Role> empRoles = new HashSet<>();
//			empRoles.add(roleService.findByName("employee"));
//
//			
//			userService.save(new AppUser(null, "marwan@gmail.com", "marwan", "123","21", "00221515020", "male", userRoles));
//		}
//		if (comService.findall().isEmpty()) {
//			Set<Post> adminRoles = new HashSet<>();
//			Long id = (long) 1 ;
//			adminRoles.add(postService.findById(id));
//			
//			AppUser appUser = new AppUser();
//			appUser.setId(id);
//			comService.save(new Comment(null, "hi marwan",appUser,adminRoles));
//			comService.save(new Comment(null, "hi hi hi ",appUser,adminRoles));
//		
//			
////			userService.save(new AppUser(null, "marwan@gmail.com", "marwan", "123", "22","01070169530","male", adminRoles));
//		}
	}
}
