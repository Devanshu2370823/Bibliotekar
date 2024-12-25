package com.cts.bibliotekar.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.bibliotekar.entity.User;

public class MyUserDetail implements UserDetails {
	
	private User user;
	
	public MyUserDetail(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())); 
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
	}

}
