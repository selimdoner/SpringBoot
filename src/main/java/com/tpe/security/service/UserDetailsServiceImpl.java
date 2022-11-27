package com.tpe.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tpe.domain.MyUser;
import com.tpe.domain.Role;
import com.tpe.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser= userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("User not found:"+username));
		//myUser->UserDetails
		
		return new User(myUser.getUserName(),myUser.getPassword(),buildGrantedAuthority(myUser.getRoles()));
		
	}
	
	private List<SimpleGrantedAuthority> buildGrantedAuthority(Set<Role> roles){
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
		
		for(Role role:roles) {
			authorities.add(new SimpleGrantedAuthority(role.getType().name()));
		}

		return authorities;
	}

}
