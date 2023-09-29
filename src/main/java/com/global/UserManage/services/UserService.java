package com.global.UserManage.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.ProjectManage.error.RecordNotFoundException;
import com.global.ProjectManage.repository.FriendRepo;
import com.global.UserManage.entity.AppUser;
import com.global.UserManage.repository.UserRepo;
import com.global.UserManage.secEntity.UserDetail;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepo repo;
	private final FriendRepo friendRepo;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;

	public List<AppUser> findAllUsersNotFriendsWithCurrentUser() {
		// Get the currently authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Ensure that the user is authenticated and has a username
		if (authentication != null && authentication.isAuthenticated()) {
			String currentUsername = authentication.getName();

			// Find the current user by username
			AppUser currentUser = finduser(currentUsername);

			// Find friends of the current user
			Set<Long> friendIds = friendRepo.findFriendIdsByUserId(currentUser.getId());

			// Retrieve all users except the current user and their friends
			List<AppUser> allUsers = repo.findAll();
			return allUsers.stream()
					.filter(user -> !user.getId().equals(currentUser.getId()) && !friendIds.contains(user.getId()))
					.collect(Collectors.toList());
		} else {
			// Handle the case when no user is authenticated
			return Collections.emptyList(); // or throw an exception
		}
	}
	public Optional<AppUser> findById(Long id) {
		Optional<AppUser> entity = repo.findById(id);
		if (entity.isPresent()) {
			return entity;
		} else {
			throw new RecordNotFoundException("this record with id : " + id + "  Not Found");
		}
	}

	public boolean existByEmail(String email) {
		return repo.existsByEmail(email);
	}

	public AppUser save(AppUser entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		entity.getRole().add(roleService.findAll().get(1));
		entity.setDate(LocalDate.now());
		return repo.save(entity);
	}

	public AppUser getUserWithPostsAndComments(Long userId) {
		return repo.findUserWithPostsAndComments(userId);
	}

	public AppUser finduser(String email) {

		return repo.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser userApp = repo.findByEmail(username);

		if (userApp == null) {
			throw new UsernameNotFoundException("this user not found with selected user name :-  " + username);
		}
		return new UserDetail(userApp);
	}
}
