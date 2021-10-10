package com.completewebapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.completewebapplication.config.CustomUserDetails;
import com.completewebapplication.entity.User;
import com.completewebapplication.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(email);
		if(user==null)
		{
			throw new UsernameNotFoundException("User Not Found......");
		}
		return new CustomUserDetails(user);
	}

}
