package com.global.UserManage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.ProjectManage.error.RecordNotFoundException;
import com.global.UserManage.entity.AppUser;
import com.global.UserManage.repository.UserRepo;
import com.global.UserManage.secEntity.UserDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepo repo;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;

	public List<AppUser> findall() {
		return repo.findAll();
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
