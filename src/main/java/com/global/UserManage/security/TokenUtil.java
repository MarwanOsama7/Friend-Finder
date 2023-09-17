package com.global.UserManage.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.global.UserManage.entity.AppUser;
import com.global.UserManage.secEntity.JwtToken;
import com.global.UserManage.secEntity.SignInRequest;
import com.global.UserManage.services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {

	private final String CLAIMS_SUBJECT = "sub";
	private final String CLAIMS_CREATED = "created";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Value("${auth.expiration}")
	private Long Token_Validate;

	@Value("${auth.secret}")
	private String TOKEN_SECRET;

	public String generateToken(UserDetails userDetails) {

		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
		claims.put(CLAIMS_CREATED, new Date());

		return Jwts.builder().setClaims(claims).setExpiration(calcTokenExpirationDate())
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET).compact();
	}

	private Date calcTokenExpirationDate() {
		return new Date(System.currentTimeMillis() + Token_Validate * 1000);
	}

	public String getUserNameFromToken(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}

	private Claims getClaims(String token) {

		return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = getUserNameFromToken(token);

		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

	private boolean isTokenExpired(String token) {
		Date expiration = getClaims(token).getExpiration();

		return expiration.before(new Date());
	}
	
	public JwtToken signin(SignInRequest signInRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
		String token = generateToken(userDetails);
		AppUser appUser = userService.finduser(signInRequest.getUsername());
		JwtToken jwtToken = new JwtToken(appUser.getId(),signInRequest.getUsername(),token);
		return jwtToken;
	}
}
