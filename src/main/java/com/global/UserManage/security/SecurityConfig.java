package com.global.UserManage.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public final String[] PUBLIC_ENDPOINTS = { "/api/v1/auth/**", "/v3/api-docs/**", "/v3/api-docs.yaml","/swagger-ui.html",
			"/swagger-ui/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests().antMatchers(PUBLIC_ENDPOINTS).permitAll().anyRequest().authenticated().and()
				.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Bean
	AuthFilter authFilter() {
		return new AuthFilter();
	}
}
