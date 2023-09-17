package com.global.UserManage.secEntity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.global.UserManage.entity.AppUser;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public  class UserDetail  implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	
	private String email;
	
	@SuppressWarnings("unused")
	private String userName; 
	
	private String password;
	
	
	
	public UserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetail(AppUser user) {
		super();
		this.id=user.getId();
		this.email=user.getEmail();
		this.userName=user.getFirstname();
		this.password=user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
